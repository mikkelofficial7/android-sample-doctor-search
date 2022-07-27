package com.android.mobile.alteacaretest.network.api

import com.android.mobile.alteacaretest.BuildConfig
import com.android.mobile.alteacaretest.model.ResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("v3/{id}")
    suspend fun loadAllDoctor(
        @Path("id") id: String = BuildConfig.UrlPath
    ): Response<ResponseData>
}