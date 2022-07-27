package com.android.mobile.alteacaretest.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.mobile.alteacaretest.network.repository.MovieRepository
import com.android.mobile.alteacaretest.state.MovieTop250State
import kotlinx.coroutines.launch

class MovieTop250ViewModel @ViewModelInject constructor(private val repository: MovieRepository) : ViewModel() {
    private var movieTop250LiveData = MutableLiveData<MovieTop250State>()

    fun getMovieTop250LiveData(): MutableLiveData<MovieTop250State> {
        viewModelScope.launch {
            movieTop250LiveData.postValue(MovieTop250State.Loading)

            repository.getTop250Movie().let {
                if(it.isSuccessful)  {
                    it.body()?.let { data ->
                        movieTop250LiveData.postValue(MovieTop250State.SuccessLoad(data))
                    }
                }
                else {
                    movieTop250LiveData.postValue(MovieTop250State.FailedLoad)
                }
            }
        }

        return movieTop250LiveData
    }
}