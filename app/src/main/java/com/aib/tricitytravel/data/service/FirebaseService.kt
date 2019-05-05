/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.data.service

import com.aib.tricitytravel.data.dto.api.Stops
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface FirebaseService {

    @GET("downloadBusStopsFromFirebase")
    fun getStopsFromFirebaseAsync(): Deferred<Stops>
}