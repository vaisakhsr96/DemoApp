package com.example.demoapp.data.pager3

import androidx.paging.PagingConfig

object PagingConfig {
    val pagingConfig = PagingConfig(
        pageSize = 20,
        prefetchDistance = 10
    )
}