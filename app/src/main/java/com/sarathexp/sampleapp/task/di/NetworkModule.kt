package com.sarathexp.sampleapp.task.di

import com.sarathexp.sampleapp.task.BuildConfig
import com.sarathexp.sampleapp.task.data.remote.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesUnsplashApi(): UserApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(providesOkHttpClient())
            .build()
            .create(UserApi::class.java)
    }

    private fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }
}
