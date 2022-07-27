package com.android.mobile.alteacaretest.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.mobile.alteacaretest.network.repository.MovieRepository
import com.android.mobile.alteacaretest.state.MovieInTheaterState
import kotlinx.coroutines.launch

class MovieInTheaterViewModel @ViewModelInject constructor(private val repository: MovieRepository) : ViewModel() {
    private var movieLiveData = MutableLiveData<MovieInTheaterState>()

    fun getMovieInTheaterLiveData(): MutableLiveData<MovieInTheaterState> {
        viewModelScope.launch {
            movieLiveData.postValue(MovieInTheaterState.Loading)

            repository.getInTheaterMovie().let {
                if(it.isSuccessful)  {
                    it.body()?.let { data ->
                        movieLiveData.postValue(MovieInTheaterState.SuccessLoad(data))
                    }
                }
                else {
                    movieLiveData.postValue(MovieInTheaterState.FailedLoad)
                }
            }
        }

        return movieLiveData
    }
}