package com.example.demoapp.data.pager3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.demoapp.data.model.immerSphere.Data
import com.example.demoapp.databinding.CellUserItemsBinding

//class MyPagingDataAdapter : RecyclerView.Adapter<MyPagingDataAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        // Create a new ViewHolder.
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_user_items, parent, false)
//        return ViewHolder(view)
//    }




    class MyPagingDataAdapter: PagingDataAdapter<Data, MyPagingDataAdapter.MovieViewHolder>(MovieComparator) {

        override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
            val movie = getItem(position)!!
            holder.view.tvName.text = movie.name
           // Glide.with(holder.itemView.context).load("https://image.tmdb.org/t/p/w300"+movie.poster_path).into(holder.view.imageview)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
           // val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_user_items, parent, false)
            val binding = CellUserItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)

//            val binding = CellUserItemsBinding.inflate(inflater, parent, false)
            return MovieViewHolder(binding)
        }

        class MovieViewHolder(val view: CellUserItemsBinding): RecyclerView.ViewHolder(view.root)

        object MovieComparator: DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                // Id is unique.
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }
        }
    }