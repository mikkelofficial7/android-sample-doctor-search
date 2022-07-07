package com.android.mobile.alteacaretest.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

object NetworkCall {
    private const val CODE_SUCCESS = 200

    fun <T>process(call: Call<T>, responseCallback: ResponseCallback<T>) {

        val callback = object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                handleResponse(response, responseCallback)
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                val errorCode = throwable.message.orEmpty()
                val message = throwable.localizedMessage.orEmpty()

                responseCallback.onError(errorCode, message)
            }
        }

        call.enqueue(callback)
    }

    private fun <T>handleResponse(response: Response<T>, responseCallback: ResponseCallback<T>) {
        val responseBody = response.body() as T

        when(response.code()) {
            CODE_SUCCESS -> responseCallback.onSuccess(responseBody)
            else -> {
                val errorCode = response.code().toString()
                val message = response.message()

                responseCallback.onError(errorCode, message)
            }
        }
    }

    fun <T>provideRequest(clazz: Class<T>) : T {
        return NetworkRequest.create(clazz)
    }
}