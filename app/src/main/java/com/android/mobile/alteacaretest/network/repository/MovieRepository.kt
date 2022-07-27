package com.android.mobile.alteacaretest.network.repository

import com.android.mobile.alteacaretest.network.api.ApiService
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getMostPopularMovie(language: String = "en") =  apiService.getMostPopularMovie(language = language)
    suspend fun getTop250Movie(language: String = "en") =  apiService.getTop250Movie(language = language)
    suspend fun getInTheaterMovie(language: String = "en") =  apiService.getInTheaterMovie(language = language)
    suspend fun getComingSoonMovie(language: String = "en") =  apiService.getComingSoonMovie(language = language)
    suspend fun getBoxOfficeMovie(language: String = "en") =  apiService.getBoxOffice(language = language)

}