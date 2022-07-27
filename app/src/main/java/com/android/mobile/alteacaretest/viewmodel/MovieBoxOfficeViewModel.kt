package com.android.mobile.alteacaretest.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.mobile.alteacaretest.network.repository.MovieRepository
import com.android.mobile.alteacaretest.state.MovieBoxOfficeState
import kotlinx.coroutines.launch

class MovieBoxOfficeViewModel @ViewModelInject constructor(private val repository: MovieRepository) : ViewModel() {
    private var movieLiveData = MutableLiveData<MovieBoxOfficeState>()

    fun getMovieBoxOfficeLiveData(): MutableLiveData<MovieBoxOfficeState> {
        viewModelScope.launch {
            movieLiveData.postValue(MovieBoxOfficeState.Loading)

            repository.getBoxOfficeMovie().let {
                if(it.isSuccessful)  {
                    it.body()?.let { data ->
                        movieLiveData.postValue(MovieBoxOfficeState.SuccessLoad(data))
                    }
                }
                else {
                    movieLiveData.postValue(MovieBoxOfficeState.FailedLoad)
                }
            }
        }

        return movieLiveData
    }
}