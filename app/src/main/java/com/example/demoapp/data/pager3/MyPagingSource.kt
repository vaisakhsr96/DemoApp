package com.example.demoapp.data.pager3

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.demoapp.data.api.RetrofitService
import com.example.demoapp.data.model.MovieModelNew.Movie


class MoviePagingSource(private val apiService: RetrofitService): PagingSource<Int, Movie>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        return try {
            val position = params.key ?: 1
            val response = apiService.getTopRatedMovies("e8d648003bd11b5c498674fbd4905525","en-US",position)
            LoadResult.Page(data = response.body()!!.results, prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
    @OptIn(androidx.paging.ExperimentalPagingApi::class)
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}