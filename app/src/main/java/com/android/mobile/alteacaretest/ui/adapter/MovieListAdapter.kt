package com.android.mobile.alteacaretest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.mobile.alteacaretest.databinding.ItemMovieListBinding
import com.android.mobile.alteacaretest.model.detail.MovieList
import com.android.mobile.alteacaretest.ui.viewholder.MovieListViewHolder
import kotlin.collections.ArrayList

class MovieListAdapter(val onDetailClick: (MovieList) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    private var listMovie: ArrayList<MovieList> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieListViewHolder).bindHolder(listMovie[position], onDetailClick)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    fun addData(list: ArrayList<MovieList>) {
        listMovie = list
        notifyDataSetChanged()
    }
}