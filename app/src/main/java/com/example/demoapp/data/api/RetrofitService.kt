package com.example.demoapp.data.api

import com.example.demoapp.data.model.MovieModelNew.MovieResponse
import com.example.demoapp.data.model.immerSphere.SearchListModel
import com.example.demoapp.data.model.immerSphere.login.LoginReq
import com.example.demoapp.data.model.immerSphere.login.response.LoginResponse
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface RetrofitService {


    @POST("auth/signin")
    suspend fun Login(
        @Body loginReq: LoginReq
    ): LoginResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("assets-list?")
    suspend fun getSearchList(
        @Header("Authorization") Authorization: String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImFqYXlqb3NlQHJldnlyaWVnbG9iYWwuY29tIiwic3ViIjoiZDM5OTIwOTgtYTVhMi00YWYxLTgyNzItM2MxNmMyNjdmYjNkIiwiaWF0IjoxNjg2NzM4MjMzLCJleHAiOjE2ODY4MjQ2MzN9.saazVR39vW1RXvERdmOU31pvD9TFHiuvrFiG1utJV7g",
        @Query(/* value = */ "range") range: String,
        @Query(/* value = */ "sort") sort: String = "[\"created_at\", \"ASC\"]",
        @Query(/* value = */ "filter") filter: String = "{\"type\":\"2\",\"q\":\"" + " " + "\",\"from\":\"mobile\",\"is_active\":true}"
    ): SearchListModel


    companion object {
        var interceptor = HttpLoggingInterceptor()

        var baseUrl = "https://apistaging.immersphere.io/api/"

        //var baseUrl = "https://api.themoviedb.org/3/"
        val gson = GsonBuilder().setLenient().create()

        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build()
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)

//                val retrofit = Retrofit.Builder()
//                    .baseUrl(baseUrl)
                    // .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson)).build()

//                    .addInterceptor(interceptor)
//                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

    }
}