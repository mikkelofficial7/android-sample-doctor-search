package com.android.mobile.alteacaretest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.mobile.alteacaretest.databinding.ItemMovieBoxOfficeBinding
import com.android.mobile.alteacaretest.model.detail.MovieBoxOffice
import com.android.mobile.alteacaretest.ui.viewholder.MovieBoxOfficeViewHolder
import kotlin.collections.ArrayList

class MovieBoxOfficeAdapter(val onDetailClick: (MovieBoxOffice) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    private var listMovie: ArrayList<MovieBoxOffice> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = ItemMovieBoxOfficeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieBoxOfficeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieBoxOfficeViewHolder).bindHolder(listMovie[position], onDetailClick)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    fun addData(list: ArrayList<MovieBoxOffice>) {
        listMovie = list
        notifyDataSetChanged()
    }
}