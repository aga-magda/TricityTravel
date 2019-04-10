package com.aib.tricitytravel.ui.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.aib.tricitytravel.R
import com.aib.tricitytravel.ui.BaseFragment

class MainFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {
                Navigation.findNavController(view!!).navigate(R.id.action_mainFragment_to_settingsFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
