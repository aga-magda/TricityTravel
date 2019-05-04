/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.data.dto.api

data class WeatherResponse(
    val weather: List<Weather> = listOf(),
    val main: Main = Main(),
    val wind: Wind = Wind()
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String
)

data class Main(
    val temp: Double = 0.00,
    val pressure: Double = 0.00,
    val humidity: Double = 0.00,
    val temp_min: Double = 0.00,
    val temp_max: Double = 0.00
)

data class Wind(
    val speed: Double = 0.00,
    val deg: Double = 0.00
)
