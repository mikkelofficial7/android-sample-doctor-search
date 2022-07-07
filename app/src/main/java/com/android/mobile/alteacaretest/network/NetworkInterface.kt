package com.android.mobile.alteacaretest.network

import com.android.mobile.alteacaretest.BuildConfig
import com.android.mobile.alteacaretest.model.ResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkInterface {
    @GET("v3/{id}")
    fun loadAllDoctor(@Path("id") id: String = BuildConfig.UrlPath): Call<ResponseData>
}