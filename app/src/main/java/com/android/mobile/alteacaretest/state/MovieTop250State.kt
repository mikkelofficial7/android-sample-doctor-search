package com.android.mobile.alteacaretest.state

import com.android.mobile.alteacaretest.model.MovieTop250ResponseData

sealed class MovieTop250State {
    object Loading : MovieTop250State()
    data class SuccessLoad(val moviePopularResponse: MovieTop250ResponseData): MovieTop250State()
    object FailedLoad : MovieTop250State()
}