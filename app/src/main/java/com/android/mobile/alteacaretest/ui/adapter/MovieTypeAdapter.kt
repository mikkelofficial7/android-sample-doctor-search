package com.android.mobile.alteacaretest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.mobile.alteacaretest.databinding.ItemMovieTypeListBinding
import com.android.mobile.alteacaretest.model.MovieType
import com.android.mobile.alteacaretest.ui.viewholder.MovieTypeViewHolder

class MovieTypeAdapter(val onMovieTypeClick:(MovieType) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    private var listMovieType = ArrayList<MovieType>()
    private var lastMovieTypeClick : MovieType? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = ItemMovieTypeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieTypeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieTypeViewHolder).bindHolder(listMovieType[position], lastMovieTypeClick, onMovieTypeClick)
    }

    override fun getItemCount(): Int {
        return listMovieType.size
    }

    fun setLastMovieTypeClick(type: MovieType) {
        lastMovieTypeClick = type
        notifyDataSetChanged()
    }

    fun addCategory(list: ArrayList<MovieType>) {
        listMovieType = list
        setLastMovieTypeClick(listMovieType.first())

        notifyDataSetChanged()
    }
}