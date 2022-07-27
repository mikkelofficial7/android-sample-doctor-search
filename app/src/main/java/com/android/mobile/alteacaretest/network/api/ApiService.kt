package com.android.mobile.alteacaretest.network.api

import com.android.mobile.alteacaretest.BuildConfig
import com.android.mobile.alteacaretest.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("{lang}/API/MostPopularMovies/{apikey}")
    suspend fun getMostPopularMovie(
        @Path("lang") language: String = "en",
        @Path("apikey") apikey: String = BuildConfig.ApikeyPath
    ): Response<MoviePopularResponseData>

    @GET("{lang}/API/Top250Movies/{apikey}")
    suspend fun getTop250Movie(
        @Path("lang") language: String = "en",
        @Path("apikey") apikey: String = BuildConfig.ApikeyPath
    ): Response<MovieTop250ResponseData>

    @GET("{lang}/API/InTheaters/{apikey}")
    suspend fun getInTheaterMovie(
        @Path("lang") language: String = "en",
        @Path("apikey") apikey: String = BuildConfig.ApikeyPath
    ): Response<MovieInTheaterResponseData>

    @GET("{lang}/API/ComingSoon/{apikey}")
    suspend fun getComingSoonMovie(
        @Path("lang") language: String = "en",
        @Path("apikey") apikey: String = BuildConfig.ApikeyPath
    ): Response<MovieComingSoonResponseData>

    @GET("{lang}/API/BoxOffice/{apikey}")
    suspend fun getBoxOffice(
        @Path("lang") language: String = "en",
        @Path("apikey") apikey: String = BuildConfig.ApikeyPath,
    ): Response<MovieBoxOfficeResponse>
}