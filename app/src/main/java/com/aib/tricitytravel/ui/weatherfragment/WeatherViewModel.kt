/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.ui.weatherfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aib.tricitytravel.R
import com.aib.tricitytravel.TricityTravel
import com.aib.tricitytravel.data.WeatherRepository
import com.aib.tricitytravel.data.dto.api.WeatherResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    val temp = MutableLiveData<Double>(0.00)
    val city = MutableLiveData<String>("")
    val humidity = MutableLiveData<String>("")
    val wind = MutableLiveData<String>("")

    suspend fun getWeather(city: String) {
        val weatherResponse = repository.getWeather(city)
        assignValues(weatherResponse)
        this.city.postValue(city)
        _isLoading.postValue(false)
    }

    fun refreshWeather() {
        GlobalScope.launch {
            _isLoading.postValue(true)
            val weatherResponse = repository.getWeather(city.value!!)
            assignValues(weatherResponse)
            _isLoading.postValue(false)
        }
    }

    private fun assignValues(weatherResponse: WeatherResponse) {
        temp.postValue(weatherResponse.main.temp)
        humidity.postValue(TricityTravel.res?.getString(R.string.humidity) + ": " + weatherResponse.main.humidity.toString() + " %")
        wind.postValue(TricityTravel.res?.getString(R.string.wind) + ": " + Math.round((weatherResponse.wind.speed * 3.6) * 100.0) / 100.0 + " km/h")
    }
}