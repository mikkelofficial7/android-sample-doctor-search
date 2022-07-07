package com.android.mobile.alteacaretest.network

import com.android.mobile.alteacaretest.BuildConfig
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkRequest {
    private var retrofit: Retrofit

    init {
        val gson = GsonBuilder().setLenient().create()

        val retrofitBuilder = Retrofit
            .Builder()
            .baseUrl(BuildConfig.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))

        retrofit = retrofitBuilder.build()
    }

    fun <T> create(clazz: Class<T>): T {
        return retrofit.create(clazz) as T
    }
}