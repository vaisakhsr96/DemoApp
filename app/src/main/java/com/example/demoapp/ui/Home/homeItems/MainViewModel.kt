package com.example.demoapp.ui.Home.homeItems

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.demoapp.data.api.RetrofitService
import com.example.demoapp.data.model.immerSphere.Data
import com.example.demoapp.data.model.MovieModelNew.Movie
import com.example.demoapp.data.model.state.CommentApiState
import com.example.demoapp.data.model.state.Status
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.Locale

class MainViewModel : ViewModel() {
    val retrofitService = RetrofitService
    private val _items = MutableStateFlow<PagingData<Data>>(PagingData.empty())
    val items: StateFlow<PagingData<Data>> = _items
    val _commentState = MutableLiveData<CommentApiState<PagingData<Data>>>()

    val commentState = MutableStateFlow(
        CommentApiState(
            Status.LOADING,
            _items, ""
        )
    )
    val mainRepository: MainRepository = MainRepository(retrofitService.getInstance())
    val errorMessage = MutableLiveData<String>()

    fun getMovieList(): LiveData<PagingData<Movie>> {
        return mainRepository.getAllMovies()?.cachedIn(viewModelScope)!!
    }

    fun getSearchList(): LiveData<PagingData<Data>> {
        return mainRepository.getSearchList()?.cachedIn(viewModelScope)!!
    }

    fun loadItems() {
//        viewModelScope.launch {
////            mainRepository?.getItems()?.collectLatest { pagingData ->
////                 _items.value = pagingData
//
//            mainRepository.getItems().map { it ->
//                it.map {
//                    it.copy(name = it.name?.uppercase(Locale.getDefault()))
//
//                }
//
//            }
//                .collectLatest {
//
//                    _items.value = it
//                     CommentApiState.success(it)
//                }
////val uppercase = _items.
//
//        }

        viewModelScope.launch {
            try {
                mainRepository.getItems().map { it ->
                    it.map {

                        it.copy(name = it.name?.uppercase(Locale.getDefault()))
                    }
                }.collectLatest {  transformedResponse->
                    //val transformedList = transformedResponse.toString()


                    //println("Log ----->1${transformedList}")



                    _items.value = transformedResponse

                    _commentState.value = CommentApiState.success(transformedResponse)
//                    if (_commentState.value!!.message == null){
//                        _commentState.value = CommentApiState.error("An error occurred: value is empty")
//
//                    }
                }
            }
            catch (e: Exception) {
                println("Log ----->1${e.message}")
                _commentState.value = CommentApiState.error("An error occurred: ${e.message}")
            }
        }
    }

}