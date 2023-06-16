package com.example.demoapp.data.pager3

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Loading<out T>(val data: T? = null) : Resource<T>()
    data class Failure(val message: String) : Resource<String>()
}


