/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.ui.carfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aib.tricitytravel.R
import com.aib.tricitytravel.TricityTravel
import com.aib.tricitytravel.data.MapsRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CarViewModel @Inject constructor(
    private val repository: MapsRepository
) : ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isSummaryVisible = MutableLiveData(false)
    val isSummaryVisible: LiveData<Boolean> = _isSummaryVisible

    private val _summaryTime = MutableLiveData("")
    val summaryTime: LiveData<String> = _summaryTime

    private val _status = MutableLiveData(ViewStatus.OK)
    val status: LiveData<ViewStatus> = _status

    fun calculateRoute(startPosition: String?, endPosition: String?) {
        if (!startPosition.isNullOrEmpty() && !endPosition.isNullOrEmpty()) {
            GlobalScope.launch {
                _status.postValue(ViewStatus.OK)
                _isSummaryVisible.postValue(true)
                _isLoading.postValue(true)
                val route = repository.calculateRoute(startPosition, endPosition)
                if (route.response.route.isNotEmpty()) {
                    _summaryTime.postValue((route.response.route.first().summary.trafficTime / 60).toString() + " min")
                } else {
                    _status.postValue(ViewStatus.LOCATIONS_ERROR)
                    _isSummaryVisible.postValue(false)
                }
                _isLoading.postValue(false)
            }
        } else {
            _status.postValue(ViewStatus.EMPTY_FIELDS)
        }
    }
}