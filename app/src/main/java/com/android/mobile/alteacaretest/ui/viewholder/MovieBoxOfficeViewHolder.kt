package com.android.mobile.alteacaretest.ui.viewholder

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.android.mobile.alteacaretest.R
import com.android.mobile.alteacaretest.databinding.ItemMovieBoxOfficeBinding
import com.android.mobile.alteacaretest.model.detail.MovieBoxOffice
import com.bumptech.glide.Glide

class MovieBoxOfficeViewHolder(private val binding: ItemMovieBoxOfficeBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bindHolder(movie: MovieBoxOffice, onDetailClick:(MovieBoxOffice) -> Unit) {
        binding.ivCrown.isVisible = movie.rank.toInt() < 4

        Glide.with(binding.root.context).load(movie.image).into(binding.ivMoviePoster)
        binding.tvTitle.text = movie.title
        binding.tvRank.text = movie.rank
        binding.tvGross.text = binding.root.context.getString(R.string.movie_profit, movie.gross, movie.weeks)

        binding.root.setOnClickListener {
            onDetailClick(movie)
        }
    }
}