/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.ui.settingsfragment.selectstopfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aib.tricitytravel.data.StopsRepository
import com.aib.tricitytravel.data.dto.FavoriteStop
import com.aib.tricitytravel.data.dto.api.Stop
import javax.inject.Inject

class SettingsSelectStopViewModel @Inject constructor(
    private val repository: StopsRepository
) : ViewModel() {

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _stopItems = MutableLiveData<List<Stop>>()
    val stopItems: LiveData<List<Stop>> = _stopItems

    suspend fun getStops() {
        val stops = repository.getAllStops()
        _stopItems.postValue(stops)

        _isLoading.postValue(false)
    }

    suspend fun refreshStops() {
        _isLoading.postValue(true)
        repository.replaceStopsInDb()
        _isLoading.postValue(false)
    }

    suspend fun addFavoriteStop(favoriteStop: FavoriteStop) {
        repository.addFavoriteStop(favoriteStop)
    }
}