package com.android.mobile.alteacaretest.ui.viewholder

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.mobile.alteacaretest.R
import com.android.mobile.alteacaretest.databinding.ItemMovieTypeListBinding
import com.android.mobile.alteacaretest.model.MovieType

class MovieTypeViewHolder(private val binding: ItemMovieTypeListBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bindHolder(type: MovieType, lastMovieTypeClick: MovieType?, onMovieTypeClick:(MovieType) -> Unit) {

        binding.tvMovieType.text = type.title
        binding.tvMovieType.setTextColor(
            ContextCompat.getColor(
                binding.root.context,
                R.color.white
            )
        )

        binding.root.setOnClickListener {
            if (lastMovieTypeClick?.id == type.id) return@setOnClickListener
            onMovieTypeClick(type)
        }

        if (lastMovieTypeClick?.id == type.id) {
            binding.tvMovieType.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.black
                )
            )
        }
    }
}