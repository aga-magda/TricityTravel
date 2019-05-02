package com.aib.tricitytravel.data.dto.api

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Stop(
    val directions: List<String>,
    @PrimaryKey val stopId: String,
    val stopDesc: String,
    val routeIds: List<String>
)