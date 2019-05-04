package com.aib.tricitytravel.ui.publictransportfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aib.tricitytravel.data.StopsRepository
import com.aib.tricitytravel.data.dto.FavoriteStop
import javax.inject.Inject

class PublicTransportViewModel @Inject constructor(
    private val repository: StopsRepository
) : ViewModel() {

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _stopItems = MutableLiveData<List<FavoriteStop>>()
    val stopItems: LiveData<List<FavoriteStop>> = _stopItems

    suspend fun getFavoriteStops() {
        val stops = repository.getFavoriteStops()
        _stopItems.postValue(stops)

        _isLoading.postValue(false)
    }

    suspend fun deleteFavoriteStop(favoriteStop: FavoriteStop) {
        repository.deleteFavoriteStop(favoriteStop)
    }
}