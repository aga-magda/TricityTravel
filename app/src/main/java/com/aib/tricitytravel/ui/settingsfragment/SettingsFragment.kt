package com.aib.tricitytravel.ui.settingsfragment

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.aib.tricitytravel.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}
