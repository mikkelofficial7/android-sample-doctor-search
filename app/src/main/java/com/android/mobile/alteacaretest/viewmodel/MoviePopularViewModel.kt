package com.android.mobile.alteacaretest.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.mobile.alteacaretest.network.repository.MovieRepository
import com.android.mobile.alteacaretest.state.MoviePopularState
import kotlinx.coroutines.launch

class MoviePopularViewModel @ViewModelInject constructor(private val repository: MovieRepository) : ViewModel() {
    private var moviePopularLiveData = MutableLiveData<MoviePopularState>()

    fun getMoviePopularLiveData(): MutableLiveData<MoviePopularState> {
        viewModelScope.launch {
            moviePopularLiveData.postValue(MoviePopularState.Loading)

            repository.getMostPopularMovie().let {
                if(it.isSuccessful)  {
                    it.body()?.let { data ->
                        moviePopularLiveData.postValue(MoviePopularState.SuccessLoad(data))
                    }
                }
                else {
                    moviePopularLiveData.postValue(MoviePopularState.FailedLoad)
                }
            }
        }

        return moviePopularLiveData
    }
}