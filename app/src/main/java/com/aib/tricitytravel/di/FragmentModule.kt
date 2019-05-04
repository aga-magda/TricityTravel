package com.aib.tricitytravel.di

import com.aib.tricitytravel.ui.mainfragment.MainFragment
import com.aib.tricitytravel.ui.publictransportfragment.PublicTransportFragment
import com.aib.tricitytravel.ui.publictransportfragment.StopFragment
import com.aib.tricitytravel.ui.settingsfragment.SettingsFragment
import com.aib.tricitytravel.ui.settingsfragment.selectstopfragment.SettingsSelectStopFragment
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
}