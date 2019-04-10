package com.aib.tricitytravel.data.dto.api

import com.aib.tricitytravel.data.dto.api.Delay

data class EstimatedTimes(
    val lastUpdate: String,
    val delay: List<Delay>
)