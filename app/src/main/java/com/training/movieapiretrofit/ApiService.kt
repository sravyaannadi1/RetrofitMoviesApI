package com.training.movieapiretrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.MOVIE_ENDPOINT)
    fun getMovie(
        @Query("api_key") api_key: String
    ): Call<MovieResponse>
}