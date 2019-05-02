package com.aib.tricitytravel.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aib.tricitytravel.data.dto.FavoriteStop

@Dao
interface FavoriteStopsDao {

    @Query("SELECT * FROM FavoriteStop")
    suspend fun getFavoriteStops(): List<FavoriteStop>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteStops(vararg stops: FavoriteStop)
}