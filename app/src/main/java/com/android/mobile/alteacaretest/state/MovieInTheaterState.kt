package com.android.mobile.alteacaretest.state

import com.android.mobile.alteacaretest.model.MovieInTheaterResponseData

sealed class MovieInTheaterState {
    object Loading : MovieInTheaterState()
    data class SuccessLoad(val moviePopularResponse: MovieInTheaterResponseData): MovieInTheaterState()
    object FailedLoad : MovieInTheaterState()
}