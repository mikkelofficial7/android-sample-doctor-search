package com.android.mobile.alteacaretest.network

import com.android.mobile.alteacaretest.BuildConfig
import com.android.mobile.alteacaretest.network.api.ApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RetrofitModule {
    @Provides
    fun provideBaseUrl() = BuildConfig.BaseUrl

    @Provides
    @Singleton
    fun provideRetrofit(
        BASE_URL: String
    ) : Retrofit {
         return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}