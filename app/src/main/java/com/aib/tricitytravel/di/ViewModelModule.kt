package com.aib.tricitytravel.di

import androidx.lifecycle.ViewModelProvider
import com.aib.tricitytravel.data.StopsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun provideViewModelFactory(
        stopsRepository: StopsRepository
    ): ViewModelProvider.Factory {
        return ViewModelFactory(stopsRepository)
    }
}