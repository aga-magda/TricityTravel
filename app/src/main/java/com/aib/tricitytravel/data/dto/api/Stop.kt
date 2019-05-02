package com.aib.tricitytravel.data.dto.api

data class Stop(
    val directions: List<String>,
    val stopId: String,
    val stopDesc: String,
    val routeIds: List<String>
)