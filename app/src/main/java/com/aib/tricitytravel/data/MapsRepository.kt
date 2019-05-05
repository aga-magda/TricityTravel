/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.data

import com.aib.tricitytravel.data.dto.api.RouteResponse
import com.aib.tricitytravel.data.service.HereGeocoderService
import com.aib.tricitytravel.data.service.HereRouteService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapsRepository @Inject constructor(
    private val hereGeocoderService: HereGeocoderService,
    private val hereRouteService: HereRouteService
) {

    suspend fun calculateRoute(startPosition: String, endPosition: String): RouteResponse {
        val geoStartPosition = getGeoParameters(startPosition)
        val startWaypoint = "geo!${geoStartPosition.first},${geoStartPosition.second}"
        val geoEndPosition = getGeoParameters(endPosition)
        val endWaypoint = "geo!${geoEndPosition.first},${geoEndPosition.second}"

        return if (geoStartPosition.first != 0.00 && geoEndPosition.first != 0.00)
            hereRouteService.calculateRouteAsync(startWaypoint, endWaypoint).await()
        else
            RouteResponse()
    }

    private suspend fun getGeoParameters(searchText: String): Pair<Double, Double> {
        val geoResponse = hereGeocoderService.getGeoParametersAsync(searchText).await()
        return Pair(
            geoResponse.Response.View.firstOrNull()?.Result?.firstOrNull()?.Location?.DisplayPosition?.Latitude ?: 0.00,
            geoResponse.Response.View.firstOrNull()?.Result?.firstOrNull()?.Location?.DisplayPosition?.Longitude ?: 0.00
        )
    }
}