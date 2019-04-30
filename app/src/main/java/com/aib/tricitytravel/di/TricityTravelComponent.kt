package com.aib.tricitytravel.di

import com.aib.tricitytravel.TricityTravel
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, ActivityModule::class, ViewModelModule::class, NetworkModule::class]
)
interface TricityTravelComponent {

    fun inject(application: TricityTravel)
}