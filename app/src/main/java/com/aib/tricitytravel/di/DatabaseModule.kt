package com.aib.tricitytravel.di

import android.content.Context
import androidx.room.Room
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
    fun provideSubjectsDao(database: TricityTravelDatabase): StopsDao {
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