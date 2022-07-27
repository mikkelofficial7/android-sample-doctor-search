package com.android.mobile.alteacaretest.state

import com.android.mobile.alteacaretest.model.MovieBoxOfficeResponse

sealed class MovieBoxOfficeState {
    object Loading : MovieBoxOfficeState()
    data class SuccessLoad(val movieResponse: MovieBoxOfficeResponse): MovieBoxOfficeState()
    object FailedLoad : MovieBoxOfficeState()
}