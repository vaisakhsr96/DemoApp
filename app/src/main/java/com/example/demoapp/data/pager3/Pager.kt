package com.example.demoapp.data.pager3

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.liveData
import com.example.demoapp.data.pager3.PagingConfig.pagingConfig
import com.example.demoapp.ui.Home.ItemRepository

object Pager {
    private val NETWORK_PAGE_SIZE: Int = 5
    var repository:ItemRepository= ItemRepository()
//    val pager = Pager(
//        config = pagingConfig,
//        pagingSourceFactory = { MyPagingSource(repository) }
//    )

//    val pager = Pager(config = PagingConfig(
//        pageSize = NETWORK_PAGE_SIZE,
//        enablePlaceholders = false,
//        initialLoadSize = 2
//    ),
//        pagingSourceFactory = {
//            MoviePagingSource()
//        }
//        , initialKey = 1
//    ).liveData
}