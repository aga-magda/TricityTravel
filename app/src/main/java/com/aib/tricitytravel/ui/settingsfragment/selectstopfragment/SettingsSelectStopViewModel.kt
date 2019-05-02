package com.aib.tricitytravel.ui.settingsfragment.selectstopfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aib.tricitytravel.data.StopsRepository
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
        val stops = repository.getAllStopsFromFirebase()

        _stopItems.postValue(stops)
        _isLoading.postValue(false)
    }
}