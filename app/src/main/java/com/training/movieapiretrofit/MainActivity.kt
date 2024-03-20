package com.training.movieapiretrofit

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.training.movieapiretrofit.Constants.API_KEY
import com.training.movieapiretrofit.databinding.ActivityMainBinding
import com.training.movieappmvp.view.MovieAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var apiService: ApiService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }
    private fun initView(){
        apiService= RetrofitBuilder.getRetrofit().create(ApiService::class.java)
        apiService.getMovie(API_KEY).enqueue(object :
            Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                Log.i("tag","${response.body()}")
                val movieList=ArrayList<MovieDetails>()
                val list=response.body()?.results
                for(i in list!!.indices){
                    movieList.add(MovieDetails(list[i].poster_path,list[i].title,list[i].popularity.toString(),list[i].original_language))
                }
                val adapter=MovieAdapter(movieList)
                binding.recyclerview.layoutManager=LinearLayoutManager(this@MainActivity)
                binding.recyclerview.adapter=adapter

            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("tag","${t.message}")
            }
        })
    }

}