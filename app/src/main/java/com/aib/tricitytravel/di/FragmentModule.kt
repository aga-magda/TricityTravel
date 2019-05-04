/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.di

import com.aib.tricitytravel.ui.mainfragment.MainFragment
import com.aib.tricitytravel.ui.publictransportfragment.PublicTransportFragment
import com.aib.tricitytravel.ui.publictransportfragment.StopFragment
import com.aib.tricitytravel.ui.settingsfragment.SettingsFragment
import com.aib.tricitytravel.ui.settingsfragment.selectstopfragment.SettingsSelectStopFragment
import com.aib.tricitytravel.ui.weatherfragment.WeatherFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragmentInjector(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributePublicTransportFragmentInjector(): PublicTransportFragment

    @ContributesAndroidInjector
    abstract fun contributeStopFragmentInjector(): StopFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingsFragmentInjector(): SettingsFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingsSelectStopFragmentInjector(): SettingsSelectStopFragment

    @ContributesAndroidInjector
    abstract fun contributeWeatherFragmentInjector(): WeatherFragment
}