package com.aib.tricitytravel.ui.publictransportfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aib.tricitytravel.data.StopsRepository
import com.aib.tricitytravel.data.dto.PublicTransportItem
import javax.inject.Inject

class StopViewModel @Inject constructor(
    private val repository: StopsRepository
) : ViewModel() {

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    private val _publicTransportItems = MutableLiveData<List<PublicTransportItem>>()
    val publicTransportItems: LiveData<List<PublicTransportItem>> = _publicTransportItems

    suspend fun getPublicTransportItems(stopId: Int, stopDesc: String, routeIds: String) {
        val publicTransportItems = repository.getPublicTransportItems(stopId, stopDesc, routeIds)
        _publicTransportItems.postValue(publicTransportItems)
        _isLoading.postValue(false)
    }

    suspend fun refreshPublicTransportItems(stopId: Int, stopDesc: String, routeIds: String) {
        _isRefreshing.postValue(true)
        val publicTransportItems = repository.getPublicTransportItems(stopId, stopDesc, routeIds)
        _publicTransportItems.postValue(publicTransportItems)
        _isRefreshing.postValue(false)
    }
}