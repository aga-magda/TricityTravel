/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.data

import com.aib.tricitytravel.data.dto.api.WeatherResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {

    @GET("data/2.5/weather?lang=pl&units=metric&appId=${APIKeys.OPEN_WEATHER_KEY}&")
    fun getWeatherAsync(@Query("q") city: String): Deferred<WeatherResponse>
}
