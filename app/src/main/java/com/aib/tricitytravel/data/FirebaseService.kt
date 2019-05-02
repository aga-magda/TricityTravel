package com.aib.tricitytravel.data

import com.aib.tricitytravel.data.dto.api.Stops
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface FirebaseService {

    @GET("downloadBusStopsFromFirebase")
    fun getStopsFromFirebaseAsync(): Deferred<Stops>
}