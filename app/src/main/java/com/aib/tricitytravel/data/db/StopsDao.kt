package com.aib.tricitytravel.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aib.tricitytravel.data.dto.api.Stop

@Dao
interface StopsDao {

    @Query("SELECT * FROM Stop")
    suspend fun getStops(): List<Stop>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStops(vararg stops: Stop)
}