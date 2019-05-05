/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.di

import com.aib.tricitytravel.data.*
import com.aib.tricitytravel.data.service.*
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
    fun provideHereGeocoderService(): HereGeocoderService {
        return Retrofit.Builder()
            .baseUrl(URLs.HERE_GEOCODER_DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(HereGeocoderService::class.java)
    }

    @Singleton
    @Provides
    fun provideHereRouteService(): HereRouteService {
        return Retrofit.Builder()
            .baseUrl(URLs.HERE_ROUTE_DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(HereRouteService::class.java)
    }

    @Singleton
    @Provides
    fun provideOpenWeatherService(): OpenWeatherService {
        return Retrofit.Builder()
            .baseUrl(URLs.OPEN_WEATHER_DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(OpenWeatherService::class.java)
    }

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