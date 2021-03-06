/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

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