package com.example.demoapp.data.pager3.immerSphere

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.demoapp.data.api.RetrofitService
import com.example.demoapp.data.model.immerSphere.Data

class ImmSearchListPagingSource(private val apiService: RetrofitService): PagingSource<Int, Data>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try{
            val position = params.key ?:1
            val limit = "10"
            val response = apiService.getSearchList(range = "[${position.toString()},$limit]")
            LoadResult.Page(data = response.data, prevKey = if(position == 1) null else position - 1,
            nextKey = position + 1)

        }catch (e:Exception){
            LoadResult.Error(e)
        }

    }

    @OptIn(androidx.paging.ExperimentalPagingApi::class)
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let {anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
