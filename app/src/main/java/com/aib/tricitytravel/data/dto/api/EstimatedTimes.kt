package com.aib.tricitytravel.data.dto.api

data class EstimatedTimes(
    val lastUpdate: String,
    val delay: List<Delay>
)