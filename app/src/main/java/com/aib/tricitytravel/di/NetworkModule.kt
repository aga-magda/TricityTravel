package com.aib.tricitytravel.di

import com.aib.tricitytravel.data.FirebaseService
import com.aib.tricitytravel.data.URLs
import com.aib.tricitytravel.data.ZTMService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
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
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(ZTMService::class.java)
    }

    @Singleton
    @Provides
    fun provideFirebaseService(): FirebaseService {
        return Retrofit.Builder()
            .baseUrl(URLs.FIREBASE_DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(FirebaseService::class.java)
    }
}