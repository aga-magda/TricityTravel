package com.aib.tricitytravel.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aib.tricitytravel.data.StopsRepository
import com.aib.tricitytravel.ui.settingsfragment.SettingsFragment
import com.aib.tricitytravel.ui.settingsfragment.SettingsSelectStopFragment
import com.aib.tricitytravel.ui.settingsfragment.SettingsSelectStopViewModel
import com.aib.tricitytravel.ui.settingsfragment.SettingsViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(
    private val stopsRepository: StopsRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(SettingsFragment::class.java) ->
                    SettingsViewModel()
                isAssignableFrom(SettingsSelectStopFragment::class.java) ->
                    SettingsSelectStopViewModel()
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}