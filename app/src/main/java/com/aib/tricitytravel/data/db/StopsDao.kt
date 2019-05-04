/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

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

    @Query("DELETE FROM Stop")
    suspend fun deleteStops()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStops(vararg stops: Stop)
}