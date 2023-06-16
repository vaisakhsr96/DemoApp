package com.example.demoapp.ui.Home.homeItems

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.demoapp.data.api.RetrofitService
import com.example.demoapp.data.model.immerSphere.Data
import com.example.demoapp.data.model.MovieModelNew.Movie
import com.example.demoapp.data.pager3.MoviePagingSource
import com.example.demoapp.data.pager3.immerSphere.ImmSearchListPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllMovies(): LiveData<PagingData<Movie>> {

        val NETWORK_PAGE_SIZE =2
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                MoviePagingSource(retrofitService)
            }
            , initialKey = 1
        ).liveData
    }
    suspend fun getSearchListFlow(): Flow<LiveData<PagingData<Data>>> {
        val NETWORK_PAGE_SIZE =10
        return flow {
            emit(Pager(
                config = PagingConfig(
                    pageSize = NETWORK_PAGE_SIZE,
                    enablePlaceholders = false,
                    initialLoadSize = 2
                ),
                pagingSourceFactory = {
                    ImmSearchListPagingSource(retrofitService)
                }
                , initialKey = 1
            ).liveData)
            // get the comment Data from the api
         //   val comment=apiService.getComments(id)

            // Emit this data wrapped in
            // the helper class [CommentApiState]

        }.flowOn(Dispatchers.IO)
    }
    fun getItems(): Flow<PagingData<Data>> {
        return Pager(config = PagingConfig(pageSize = 10)) {
            ImmSearchListPagingSource(retrofitService)
        }.flow
    }
    fun getSearchList(): LiveData<PagingData<Data>>{
        val NETWORK_PAGE_SIZE =10
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                ImmSearchListPagingSource(retrofitService)
            }
            , initialKey = 1
        ).liveData
    }

}