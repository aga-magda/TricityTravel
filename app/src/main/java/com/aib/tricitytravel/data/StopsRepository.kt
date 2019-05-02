package com.aib.tricitytravel.data

import com.aib.tricitytravel.data.dto.api.Stop
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StopsRepository @Inject constructor(
    private val ztmService: ZTMService,
    private val firebaseService: FirebaseService
) {

    suspend fun getAllStopsFromFirebase(): List<Stop> {
        val stopsObject = firebaseService.getStopsFromFirebaseAsync().await()
        return stopsObject.stops
    }

    fun getSavedStops() {

    }

    fun getEstimatedTimesForStop(stopId: String) {

    }
}