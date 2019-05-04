/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.di

import android.content.Context
import androidx.room.Room
import com.aib.tricitytravel.data.db.FavoriteStopsDao
import com.aib.tricitytravel.data.db.StopsDao
import com.aib.tricitytravel.data.db.TricityTravelDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(
    private val context: Context
) {

    @Singleton
    @Provides
    fun provideFavoriteStopsDao(database: TricityTravelDatabase): FavoriteStopsDao {
        return database.favoriteStopsDao()
    }

    @Singleton
    @Provides
    fun provideStopsDao(database: TricityTravelDatabase): StopsDao {
        return database.stopsDao()
    }

    @Singleton
    @Provides
    fun provideMobileWZRDatabase(): TricityTravelDatabase {
        return Room.databaseBuilder(
            this.context,
            TricityTravelDatabase::class.java, "TricityTravel-db"
        ).build()
    }
}