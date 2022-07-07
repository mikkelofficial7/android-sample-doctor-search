package com.android.mobile.alteacaretest.network


interface ResponseCallback<T>{

    fun onSuccess(responseBody: T) = Unit

    fun onError(errorCode: String, errorMessage: String) = Unit
}