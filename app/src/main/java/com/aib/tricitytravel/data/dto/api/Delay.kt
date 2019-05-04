/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.data.dto.api

data class Delay(
    val id: String,
    val delayInSeconds: String,
    val estimatedTime: String,
    val headsign: String,
    val routeId: String,
    val status: String,
    val theoreticalTime: String,
    val timestamp: String,
    val vehicleCode: String,
    val vehicleId: String
)