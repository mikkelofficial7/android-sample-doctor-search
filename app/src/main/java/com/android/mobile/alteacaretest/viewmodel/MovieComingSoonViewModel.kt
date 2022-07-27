package com.android.mobile.alteacaretest.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.mobile.alteacaretest.network.repository.MovieRepository
import com.android.mobile.alteacaretest.state.MovieComingSoonState
import kotlinx.coroutines.launch

class MovieComingSoonViewModel @ViewModelInject constructor(private val repository: MovieRepository) : ViewModel() {
    private var movieLiveData = MutableLiveData<MovieComingSoonState>()

    fun getMovieComingSoonLiveData(): MutableLiveData<MovieComingSoonState> {
        viewModelScope.launch {
            movieLiveData.postValue(MovieComingSoonState.Loading)

            repository.getComingSoonMovie().let {
                if(it.isSuccessful)  {
                    it.body()?.let { data ->
                        movieLiveData.postValue(MovieComingSoonState.SuccessLoad(data))
                    }
                }
                else {
                    movieLiveData.postValue(MovieComingSoonState.FailedLoad)
                }
            }
        }

        return movieLiveData
    }
}