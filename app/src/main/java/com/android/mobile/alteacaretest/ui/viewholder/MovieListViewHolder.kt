package com.android.mobile.alteacaretest.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.mobile.alteacaretest.R
import com.android.mobile.alteacaretest.databinding.ItemMovieListBinding
import com.android.mobile.alteacaretest.model.detail.MovieList
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MovieListViewHolder(private val binding: ItemMovieListBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bindHolder(movie: MovieList, onDetailClick:(MovieList) -> Unit) {
        binding.tvRating.visibility = View.VISIBLE
        binding.tvContentRating.visibility = View.GONE
        binding.tvReleaseDate.visibility = View.GONE
        binding.tvGenres.visibility = View.GONE

        Glide.with(binding.root.context)
            .load(movie.image)
            .apply(RequestOptions().placeholder(R.drawable.ic_empty))
            .into(binding.ivMoviePoster)

        binding.tvTitle.text = movie.fullTitle
        binding.tvRating.text = movie.rating()

        binding.root.setOnClickListener {
            onDetailClick(movie)
        }

        movie.releaseState?.let {
            binding.tvReleaseDate.visibility = View.VISIBLE
            binding.tvReleaseDate.text = it
        }

        movie.genres?.let {
            binding.tvGenres.visibility = View.VISIBLE
            binding.tvGenres.text = it
        }

        movie.imDbRating?.let {
            binding.tvRating.visibility = View.GONE
        }

        movie.contentRating?.let {
            if(it.isEmpty()) return@let

            binding.tvContentRating.visibility = View.VISIBLE
            binding.tvContentRating.text = it
        }
    }
}