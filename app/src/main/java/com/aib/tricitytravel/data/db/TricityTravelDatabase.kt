/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aib.tricitytravel.data.dto.FavoriteStop
import com.aib.tricitytravel.data.dto.ReportKeyword
import com.aib.tricitytravel.data.dto.api.Stop

@Database(entities = [Stop::class, FavoriteStop::class, ReportKeyword::class], version = 1, exportSchema = false)
@TypeConverters(RoomConverters::class)
abstract class TricityTravelDatabase : RoomDatabase() {

    abstract fun stopsDao(): StopsDao

    abstract fun favoriteStopsDao(): FavoriteStopsDao

    abstract fun keywordsDao(): KeywordsDao
}