package com.aib.tricitytravel.di

import com.aib.tricitytravel.data.URLs
import com.aib.tricitytravel.data.ZTMService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideZTMService(): ZTMService {
        return Retrofit.Builder()
            .baseUrl(URLs.ZTM_DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ZTMService::class.java)
    }
}