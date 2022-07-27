package com.android.mobile.alteacaretest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.mobile.alteacaretest.R
import com.android.mobile.alteacaretest.databinding.ActivityMainBinding
import com.android.mobile.alteacaretest.model.*
import com.android.mobile.alteacaretest.model.detail.MovieList
import com.android.mobile.alteacaretest.ui.adapter.MovieListAdapter
import com.android.mobile.alteacaretest.room.RoomHelper
import com.android.mobile.alteacaretest.state.*
import com.android.mobile.alteacaretest.ui.adapter.MovieBoxOfficeAdapter
import com.android.mobile.alteacaretest.ui.adapter.MovieTypeAdapter
import com.android.mobile.alteacaretest.viewmodel.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter: MovieListAdapter by lazy {
        MovieListAdapter(onDetailClick = {
            navigateDetailPage(it.id, it.title)
        })
    }

    private val adapterBoxOffice: MovieBoxOfficeAdapter by lazy {
        MovieBoxOfficeAdapter(onDetailClick = {
            navigateDetailPage(it.id, it.title)
        })
    }

    private val adapterMovieType: MovieTypeAdapter by lazy {
        MovieTypeAdapter(onMovieTypeClick = {
            validateLoadMovie(it)
        })
    }

    private val viewModelPopular: MoviePopularViewModel by viewModels()
    private val viewModelTop250: MovieTop250ViewModel by viewModels()
    private val viewModelInTheater: MovieInTheaterViewModel by viewModels()
    private val viewModelComingSoon: MovieComingSoonViewModel by viewModels()
    private val viewModelBoxOffice: MovieBoxOfficeViewModel by viewModels()


    @Inject
    lateinit var roomHelper: RoomHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        loadAllMovieType()
        loadAllMovieBoxOffice()
        loadAllMovieTop250()
        setupListener()
    }

    private fun setupListener() {

    }


    private fun loadAllMovieTop250() {
        val dao = roomHelper.initMovieTop250Dao()

        if(!dao?.getAllTop250Movie()?.items.isNullOrEmpty()) {
            dao?.getAllTop250Movie()?.let { addDataMovieTop250(it) }
            hideLoading()
            return
        }

        viewModelTop250.getMovieTop250LiveData().observe(this, Observer { state ->
            when(state) {
                is MovieTop250State.Loading -> {
                    showLoading()
                }
                is MovieTop250State.SuccessLoad -> {
                    dao?.insertAllTop250Movie(state.moviePopularResponse)
                    addDataMovieTop250(state.moviePopularResponse)
                    hideLoading()
                }
                is MovieTop250State.FailedLoad -> {
                    showErrorMessage()
                    hideLoading()
                }
            }
        })
    }

    private fun addDataMovieTop250(movieTop250ResponseData: MovieTop250ResponseData) {
        adapter.addData(movieTop250ResponseData.items)
    }


    private fun loadAllMoviePopular() {
        val dao = roomHelper.initMoviePopularDao()

        if(!dao?.getAllPopularMovie()?.items.isNullOrEmpty()) {
            dao?.getAllPopularMovie()?.let { addDataMoviePopular(it) }
            hideLoading()
            return
        }

        viewModelPopular.getMoviePopularLiveData().observe(this, Observer { state ->
            when(state) {
                is MoviePopularState.Loading -> {
                    showLoading()
                }
                is MoviePopularState.SuccessLoad -> {
                    dao?.insertAllPopularMovie(state.moviePopularResponse)
                    addDataMoviePopular(state.moviePopularResponse)
                    hideLoading()
                }
                is MoviePopularState.FailedLoad -> {
                    showErrorMessage()
                    hideLoading()
                }
            }
        })
    }

    private fun addDataMoviePopular(moviePopularResponseData: MoviePopularResponseData) {
        adapter.addData(moviePopularResponseData.items)
    }

    private fun loadAllMovieInTheater() {
        val dao = roomHelper.initMovieInTheaterDao()

        if(!dao?.getAllInTheaterMovie()?.items.isNullOrEmpty()) {
            dao?.getAllInTheaterMovie()?.let { addDataMovieInTheater(it) }
            hideLoading()
            return
        }

        viewModelInTheater.getMovieInTheaterLiveData().observe(this, Observer { state ->
            when(state) {
                is MovieInTheaterState.Loading -> {
                    showLoading()
                }
                is MovieInTheaterState.SuccessLoad -> {
                    dao?.insertAllInTheaterMovie(state.moviePopularResponse)
                    addDataMovieInTheater(state.moviePopularResponse)
                    hideLoading()
                }
                is MovieInTheaterState.FailedLoad -> {
                    showErrorMessage()
                    hideLoading()
                }
            }
        })
    }

    private fun addDataMovieInTheater(movieResponseData: MovieInTheaterResponseData) {
        adapter.addData(movieResponseData.items)
    }

    private fun loadAllMovieComingSoon() {
        val dao = roomHelper.initMovieComingSoonDao()

        if(!dao?.getAllComingSoonMovie()?.items.isNullOrEmpty()) {
            dao?.getAllComingSoonMovie()?.let { addDataMovieComingSoon(it) }
            hideLoading()
            return
        }

        viewModelComingSoon.getMovieComingSoonLiveData().observe(this, { state ->
            when(state) {
                is MovieComingSoonState.Loading -> {
                    showLoading()
                }
                is MovieComingSoonState.SuccessLoad -> {
                    dao?.insertAllComingSoonMovie(state.moviePopularResponse)
                    addDataMovieComingSoon(state.moviePopularResponse)
                    hideLoading()
                }
                is MovieComingSoonState.FailedLoad -> {
                    showErrorMessage()
                    hideLoading()
                }
            }
        })
    }

    private fun addDataMovieComingSoon(movieResponseData: MovieComingSoonResponseData) {
        adapter.addData(movieResponseData.items)
    }

    private fun loadAllMovieBoxOffice() {
        val dao = roomHelper.initMovieBoxOfficeDao()

        if(!dao?.getAllBoxOfficeMovie()?.items.isNullOrEmpty()) {
            dao?.getAllBoxOfficeMovie()?.let { addDataMovieBoxOffice(it) }
            hideLoading()
            return
        }

        viewModelBoxOffice.getMovieBoxOfficeLiveData().observe(this, { state ->
            when(state) {
                is MovieBoxOfficeState.Loading -> {
                }
                is MovieBoxOfficeState.SuccessLoad -> {
                    dao?.insertAllBoxOfficeMovie(state.movieResponse)
                    addDataMovieBoxOffice(state.movieResponse)
                }
                is MovieBoxOfficeState.FailedLoad -> {
                    showErrorMessage()
                }
            }
        })
    }

    private fun addDataMovieBoxOffice(movieResponseData: MovieBoxOfficeResponse) {
        adapterBoxOffice.addData(movieResponseData.items)
    }

    private fun loadAllMovieType(){
        val array = resources.getStringArray(R.array.movie_type_array)

        val movieTypes = ArrayList<MovieType>()
        for(i in array.indices) {
            movieTypes.add(MovieType(id = i, array[i]))
        }

        addDataMovieType(movieTypes)
    }

    private fun addDataMovieType(movieTypes: ArrayList<MovieType>) {
        adapterMovieType.addCategory(movieTypes)
    }

    private fun initAdapter() {
        binding.rvMovie.layoutManager = GridLayoutManager(this, 3)
        binding.rvMovie.setHasFixedSize(true)
        binding.rvMovie.adapter = adapter

        binding.rvMovieBoxOffice.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.rvMovieBoxOffice.setHasFixedSize(true)
        binding.rvMovieBoxOffice.adapter = adapterBoxOffice

        binding.rvMovieType.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.rvMovieType.setHasFixedSize(true)
        binding.rvMovieType.adapter = adapterMovieType
    }

    private fun validateLoadMovie(type: MovieType) {
        adapterMovieType.setLastMovieTypeClick(type)

        when(type.id) {
            0 -> loadAllMovieTop250()
            1 -> loadAllMoviePopular()
            2 -> loadAllMovieInTheater()
            3 -> loadAllMovieComingSoon()
        }
    }

    private fun navigateDetailPage(id: String, title: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.TAG_MOVIE_ID, id)
        intent.putExtra(DetailActivity.TAG_MOVIE_TITLE, title)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
    }

    private fun showLoading() {
        binding.pbLoading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.pbLoading.visibility = View.GONE
    }

    private fun showErrorMessage() {
        Toast.makeText(this, getString(R.string.failed_load_doctor), Toast.LENGTH_LONG).show()
    }
}