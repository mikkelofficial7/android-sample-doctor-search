package com.android.mobile.alteacaretest.network.repository

import com.android.mobile.alteacaretest.network.api.ApiService
import javax.inject.Inject

class DoctorRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllDoctor() =  apiService.loadAllDoctor()
}