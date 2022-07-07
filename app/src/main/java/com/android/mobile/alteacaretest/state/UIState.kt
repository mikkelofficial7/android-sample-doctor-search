package com.android.mobile.alteacaretest.state

import com.android.mobile.alteacaretest.model.ResponseData

sealed class UIState {
    object Loading : UIState()
    data class SuccessLoad(val response: ResponseData): UIState()
    object FailedLoad : UIState()
}