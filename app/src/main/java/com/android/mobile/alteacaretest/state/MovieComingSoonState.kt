package com.android.mobile.alteacaretest.state

import com.android.mobile.alteacaretest.model.MovieComingSoonResponseData

sealed class MovieComingSoonState {
    object Loading : MovieComingSoonState()
    data class SuccessLoad(val moviePopularResponse: MovieComingSoonResponseData): MovieComingSoonState()
    object FailedLoad : MovieComingSoonState()
}