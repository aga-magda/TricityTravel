/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.data.db

import androidx.room.*
import com.aib.tricitytravel.data.dto.FavoriteStop

@Dao
interface FavoriteStopsDao {

    @Query("SELECT * FROM FavoriteStop")
    suspend fun getFavoriteStops(): List<FavoriteStop>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteStops(vararg stops: FavoriteStop)

    @Delete
    suspend fun deleteFavoriteStops(vararg stops: FavoriteStop)
}