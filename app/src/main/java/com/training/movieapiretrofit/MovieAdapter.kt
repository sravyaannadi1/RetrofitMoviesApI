package com.training.movieappmvp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.training.movieapiretrofit.Constants
import com.training.movieapiretrofit.MovieDetails
import com.training.movieapiretrofit.databinding.ItemMovieLayoutBinding

class MovieAdapter( private val items: ArrayList<MovieDetails>):
    RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

    inner class ViewHolder(private val binding: ItemMovieLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: MovieDetails){
            val imageUrl = "${Constants.IMAGE_ENDPOINT.replace("{movie_id}",item.posterPath)}"

            // Load image using Picasso
            Picasso.get().load(imageUrl).into(binding.imageView2)
            binding.title.text=item.title
            binding.rating.text=item.popularity
            binding.language.text=item.language

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMovieLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = items.size
}