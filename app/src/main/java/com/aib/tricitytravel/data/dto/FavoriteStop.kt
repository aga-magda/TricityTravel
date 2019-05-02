package com.aib.tricitytravel.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aib.tricitytravel.data.dto.api.Stop

@Entity
data class FavoriteStop(
    var directions: List<String>,
    @PrimaryKey val stopId: String,
    val stopDesc: String,
    var routeIds: List<String>
) {
    constructor(
        stop: Stop
    ) : this(
        stop.directions,
        stop.stopId,
        stop.stopDesc,
        stop.routeIds
    )
}