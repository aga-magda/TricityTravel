/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.data

import com.aib.tricitytravel.data.dto.api.WeatherResponse
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val openWeatherService: OpenWeatherService
) {

    suspend fun getWeather(city: String): WeatherResponse {
        var weatherResponse = WeatherResponse()
        try {
            weatherResponse = openWeatherService.getWeatherAsync(city).await()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return weatherResponse
    }
}