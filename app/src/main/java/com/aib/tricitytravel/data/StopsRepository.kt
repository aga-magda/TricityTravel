package com.aib.tricitytravel.data

import android.util.Log
import com.aib.tricitytravel.data.db.FavoriteStopsDao
import com.aib.tricitytravel.data.db.RoomConverters
import com.aib.tricitytravel.data.db.StopsDao
import com.aib.tricitytravel.data.dto.FavoriteStop
import com.aib.tricitytravel.data.dto.PublicTransportItem
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

    suspend fun getFavoriteStops(): List<FavoriteStop> {
        return favoriteStopsDao.getFavoriteStops()
    }

    suspend fun addFavoriteStop(favoriteStop: FavoriteStop) {
        favoriteStopsDao.insertFavoriteStops(favoriteStop)
    }

    suspend fun deleteFavoriteStop(favoriteStop: FavoriteStop) {
        favoriteStopsDao.deleteFavoriteStops(favoriteStop)
    }

    suspend fun getPublicTransportItems(stopId: Int, stopDesc: String, routeIds: String): List<PublicTransportItem> {
        val delays = ztmService.getEstimatedTimesAsync(stopId).await().delay
        val routeIdsAsList = RoomConverters().toListOfStrings(routeIds)

        val publicTransportItems = mutableListOf<PublicTransportItem>()
        delays.forEach { delay ->
            if (routeIdsAsList.contains(delay.routeId))
                publicTransportItems.add(PublicTransportItem(delay, stopDesc))
        }
        return publicTransportItems
    }

    private suspend fun getAllStopsFromFirebase(): List<Stop> {
        val stopsObject = firebaseService.getStopsFromFirebaseAsync().await()
        Log.i("StopsRepository", "Downloaded from Firebase")
        return stopsObject.stops
    }
}