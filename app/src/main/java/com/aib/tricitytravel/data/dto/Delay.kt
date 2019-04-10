package com.aib.tricitytravel.data.dto

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