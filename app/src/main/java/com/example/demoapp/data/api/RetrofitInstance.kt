package com.example.demoapp.data.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {


    fun <S> createService(serviceClass: Class<S>): S {

        val gson = GsonBuilder().setLenient().create()

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        //OkHttpClient client = new OkHttpClient();
        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

//        val retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com").client(okHttpClient)
        val retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").client(okHttpClient)

       // val retrofit = Retrofit.Builder().baseUrl("https://apistaging.immersphere.io/api/").client(okHttpClient)

            .addConverterFactory(GsonConverterFactory.create(gson)).build()

        return retrofit.create(serviceClass)
    }

}

//package com.android.timeoff.api
//
//import com.android.timeoff.utils.Constants
//import com.google.gson.GsonBuilder
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import java.util.concurrent.TimeUnit
//
//
//object ServiceGenerator {
//    fun <S> createService(serviceClass: Class<S>): S {
//
//        val gson = GsonBuilder().setLenient().create()
//
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//
//        //OkHttpClient client = new OkHttpClient();
//        val okHttpClient = OkHttpClient().newBuilder()
//            .connectTimeout(120, TimeUnit.SECONDS)
//            .readTimeout(120, TimeUnit.SECONDS)
//            .writeTimeout(120, TimeUnit.SECONDS)
//            .addInterceptor(interceptor)
//            .build()
//
//        val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL).client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create(gson)).build()
//
//        return retrofit.create(serviceClass)
//    }
//}