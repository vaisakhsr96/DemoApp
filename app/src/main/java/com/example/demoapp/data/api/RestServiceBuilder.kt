package com.example.demoapp.data.api

object RestServiceBuilder {
    private var service: ApiService? = null
    val apiService: ApiService?
        get() {
            service =
                RetrofitInstance.createService(ApiService::class.java)
            return service
        }
}