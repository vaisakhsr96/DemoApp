package com.example.demoapp.ui.Home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.ui.AppBarConfiguration
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoapp.data.api.RetrofitService
import com.example.demoapp.data.model.state.Status
import com.example.demoapp.data.pager3.MyPagingDataAdapter
import com.example.demoapp.databinding.ActivityHomeBinding
import com.example.demoapp.ui.Home.homeItems.MainRepository
import com.example.demoapp.ui.Home.homeItems.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding

    //    private lateinit var itemViewModel: ItemViewModel
    private var itemViewModel: ItemViewModel? = null

    var adapter: ItemAdapter? = null
    private var viewModel: MainViewModel? = null
    private val pagerAdapter = MyPagingDataAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //  setSupportActionBar(binding.toolbar)

//        val navController = findNavController(R.id.nav_host_fragment_content_home)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.homeListView.layoutManager = LinearLayoutManager(this)
        // adapter = ItemAdapter() // Initialize with an empty list


        val retrofitService = RetrofitService
        val mainRepository = MainRepository(retrofitService.getInstance())
        binding.homeListView.adapter = pagerAdapter
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel?.errorMessage?.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        viewModel?.loadItems()


        lifecycleScope.launch {
            viewModel!!._commentState.observe(this@HomeActivity) { state ->
                when (state.status) {
                    Status.SUCCESS -> {
                        // Handle success state
                        val data = state.data
                        // Update UI with the data
                        if (data != null) {
                            pagerAdapter.submitData(lifecycle, data)
                        }

                    }

                    Status.ERROR -> {
                        // Handle error state
                        val errorMessage = state.message
                        Toast.makeText(this@HomeActivity,errorMessage,Toast.LENGTH_SHORT).show()

                        // Show error message to the user
                    }

                    Status.LOADING -> {
                        // Handle loading state
                        Toast.makeText(this@HomeActivity,"Loading.....",Toast.LENGTH_SHORT).show()

                        // Show loading indicator or perform other loading-related tasks
                    }
                }
            }



//            viewModel!!.getSearchList().observe(this@HomeActivity) {
//                it?.let {
//                    pagerAdapter.submitData(lifecycle, it)
//                }
//            }

//            viewModel!!.items.collectLatest {
//                it.let {
//                    pagerAdapter.submitData(lifecycle, it)
//                }
//
//            }
        }
        pagerAdapter.addLoadStateListener { loadState ->
            // Show progress bar while loading
            binding.progressBar.visibility =
                if (loadState.refresh is LoadState.Loading) View.VISIBLE else View.GONE

            // Show error message if there's an error
            val errorState = loadState.refresh as? LoadState.Error
            binding.errorMessage.visibility = if (errorState != null) View.VISIBLE else View.GONE
            binding.errorMessage.text = errorState?.error?.localizedMessage
        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_home)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
}