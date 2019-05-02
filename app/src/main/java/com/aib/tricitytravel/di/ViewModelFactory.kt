package com.aib.tricitytravel.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aib.tricitytravel.data.StopsRepository
import com.aib.tricitytravel.ui.settingsfragment.selectstopfragment.SettingsSelectStopViewModel
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
                isAssignableFrom(SettingsViewModel::class.java) ->
                    SettingsViewModel()
                isAssignableFrom(SettingsSelectStopViewModel::class.java) ->
                    SettingsSelectStopViewModel(stopsRepository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}