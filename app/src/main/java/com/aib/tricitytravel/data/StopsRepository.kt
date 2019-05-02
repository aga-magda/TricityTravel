package com.aib.tricitytravel.data

import com.aib.tricitytravel.data.db.StopsDao
import com.aib.tricitytravel.data.dto.api.Stop
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StopsRepository @Inject constructor(
    private val ztmService: ZTMService,
    private val firebaseService: FirebaseService,
    private val stopsDao: StopsDao
) {

    suspend fun getAllStops(): List<Stop> {
        return if (stopsDao.getStops().isEmpty()) {
            val firebaseStops = getAllStopsFromFirebase()
            stopsDao.insertStops(*firebaseStops.map { it }.toTypedArray())
            firebaseStops
        } else {
            stopsDao.getStops()
        }
    }

    suspend fun replaceStopsInDb() {
        stopsDao.deleteStops()
        val firebaseStops = getAllStopsFromFirebase()
        stopsDao.insertStops(*firebaseStops.map { it }.toTypedArray())
    }

    suspend fun getAllStopsFromFirebase(): List<Stop> {
        val stopsObject = firebaseService.getStopsFromFirebaseAsync().await()
        return stopsObject.stops
    }

    fun getSavedStops() {

    }

    fun getEstimatedTimesForStop(stopId: String) {

    }
}