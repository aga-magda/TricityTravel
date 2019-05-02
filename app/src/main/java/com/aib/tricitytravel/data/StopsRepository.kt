package com.aib.tricitytravel.data

import android.util.Log
import com.aib.tricitytravel.data.db.FavoriteStopsDao
import com.aib.tricitytravel.data.db.StopsDao
import com.aib.tricitytravel.data.dto.FavoriteStop
import com.aib.tricitytravel.data.dto.api.Stop
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StopsRepository @Inject constructor(
    private val ztmService: ZTMService,
    private val firebaseService: FirebaseService,
    private val stopsDao: StopsDao,
    private val favoriteStopsDao: FavoriteStopsDao
) {

    suspend fun getAllStops(): List<Stop> {
        return if (stopsDao.getStops().isEmpty()) {
            val firebaseStops = getAllStopsFromFirebase()
            stopsDao.insertStops(*firebaseStops.map { it }.toTypedArray())
            firebaseStops
        } else {
            Log.i("StopsRepository", "Get from DB")
            stopsDao.getStops()
        }
    }

    suspend fun replaceStopsInDb() {
        stopsDao.deleteStops()
        val firebaseStops = getAllStopsFromFirebase()
        stopsDao.insertStops(*firebaseStops.map { it }.toTypedArray())
    }

    suspend fun addFavoriteStop(favoriteStop: FavoriteStop) {
        favoriteStopsDao.insertFavoriteStops(favoriteStop)
    }

    suspend fun getAllStopsFromFirebase(): List<Stop> {
        val stopsObject = firebaseService.getStopsFromFirebaseAsync().await()
        Log.i("StopsRepository", "Downloaded from Firebase")
        return stopsObject.stops
    }

    fun getSavedStops() {

    }

    fun getEstimatedTimesForStop(stopId: String) {

    }
}