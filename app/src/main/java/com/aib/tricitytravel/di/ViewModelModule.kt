/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.di

import androidx.lifecycle.ViewModelProvider
import com.aib.tricitytravel.data.MapsRepository
import com.aib.tricitytravel.data.StopsRepository
import com.aib.tricitytravel.data.TrojmiastoRepository
import com.aib.tricitytravel.data.WeatherRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun provideViewModelFactory(
        stopsRepository: StopsRepository,
        weatherRepository: WeatherRepository,
        mapsRepository: MapsRepository,
        trojmiastoRepository: TrojmiastoRepository
    ): ViewModelProvider.Factory {
        return ViewModelFactory(stopsRepository, weatherRepository, mapsRepository, trojmiastoRepository)
    }
}