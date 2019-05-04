/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

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