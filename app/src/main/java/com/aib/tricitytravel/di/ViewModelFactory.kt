/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aib.tricitytravel.data.StopsRepository
import com.aib.tricitytravel.data.WeatherRepository
import com.aib.tricitytravel.ui.publictransportfragment.PublicTransportViewModel
import com.aib.tricitytravel.ui.publictransportfragment.StopViewModel
import com.aib.tricitytravel.ui.settingsfragment.selectstopfragment.SettingsSelectStopViewModel
import com.aib.tricitytravel.ui.settingsfragment.SettingsViewModel
import com.aib.tricitytravel.ui.weatherfragment.WeatherViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(
    private val stopsRepository: StopsRepository,
    private val weatherRepository: WeatherRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(SettingsViewModel::class.java) ->
                    SettingsViewModel()
                isAssignableFrom(SettingsSelectStopViewModel::class.java) ->
                    SettingsSelectStopViewModel(stopsRepository)
                isAssignableFrom(PublicTransportViewModel::class.java) ->
                    PublicTransportViewModel(stopsRepository)
                isAssignableFrom(StopViewModel::class.java) ->
                    StopViewModel(stopsRepository)
                isAssignableFrom(WeatherViewModel::class.java) ->
                    WeatherViewModel(weatherRepository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}