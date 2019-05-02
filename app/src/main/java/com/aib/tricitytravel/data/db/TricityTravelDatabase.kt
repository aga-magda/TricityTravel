package com.aib.tricitytravel.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aib.tricitytravel.data.dto.FavoriteStop
import com.aib.tricitytravel.data.dto.api.Stop

@Database(entities = [Stop::class, FavoriteStop::class], version = 1, exportSchema = false)
@TypeConverters(RoomConverters::class)
abstract class TricityTravelDatabase : RoomDatabase() {

    abstract fun stopsDao(): StopsDao

    abstract fun favoriteStopsDao(): FavoriteStopsDao
}