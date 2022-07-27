package com.android.mobile.alteacaretest.state

import com.android.mobile.alteacaretest.model.MoviePopularResponseData

sealed class MoviePopularState {
    object Loading : MoviePopularState()
    data class SuccessLoad(val moviePopularResponse: MoviePopularResponseData): MoviePopularState()
    object FailedLoad : MoviePopularState()
}