/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.data.dto.api

data class RouteResponse(
    val response: RouteResponseContent = RouteResponseContent()
)

data class RouteResponseContent(
    val route: List<RouteContent> = listOf()
)

data class RouteContent(
    val summary: RouteSummary
)

data class RouteSummary(
    val distance: Int,
    val trafficTime: Int,
    val baseTime: Int
)