package com.example.demoapp.data.api

import com.example.demoapp.data.model.immerSphere.SearchListModel
import com.example.demoapp.data.model.MovieModel.MovieListModel
import com.example.demoapp.data.model.MovieModelNew.MovieResponse
import com.example.demoapp.data.model.UserItemsModel.UserItems
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("/users")
    suspend fun getItems(): UserItems


    @GET("")
    suspend fun getMovies(@Query("apikey") apiKeyString: String = "cebf8489",
                          @Query("s") searchQuery: String = "Batman",
                          @Query("page") pageNo: Int = 1): MovieListModel


    @GET("assets-list?")
    suspend fun getSearchList(@Header("Authorization") Authorization:String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImFkbWluX2JldGFAaW1tZXJzcGhlcmUuaW8iLCJzdWIiOiJlYzhiMGFkMy03YzJlLTRhMGMtOGJkMi1mNjhlMjhjZTVmODMiLCJpYXQiOjE2ODUzMzYyNzIsImV4cCI6MTY4NTQyMjY3Mn0.xF4HEDte2IjzIcTF4gurOy9JxCLMNS_TfOHzN9B-HwE",
                              @Query(/* value = */ "range") range:String ,
                              @Query(/* value = */ "sort") sort:String ="[\"created_at\", \"ASC\"]",
                              @Query(/* value = */ "filter") filter:String ="{\"type\":\"2\",\"q\":\""+" "+"\",\"from\":\"mobile\",\"is_active\":true}"): SearchListModel




    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MovieResponse>
}