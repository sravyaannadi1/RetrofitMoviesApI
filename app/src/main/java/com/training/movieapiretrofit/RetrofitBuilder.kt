package com.training.movieapiretrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private lateinit var retrofit: Retrofit
    val loggingInterceptor= HttpLoggingInterceptor().apply {
        level=HttpLoggingInterceptor.Level.BODY
    }
    val client= OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    fun getRetrofit(): Retrofit {
        if(!::retrofit.isInitialized){
            retrofit= Retrofit.Builder().addConverterFactory(
                GsonConverterFactory.create()
            ).baseUrl(Constants.BASE_URL)
                .client(client)
                .build()
        }
        return retrofit
    }
}