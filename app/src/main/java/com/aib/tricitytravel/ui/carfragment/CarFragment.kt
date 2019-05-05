/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.ui.carfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.aib.tricitytravel.R
import com.aib.tricitytravel.databinding.FragmentCarBinding
import com.aib.tricitytravel.ui.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class CarFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(CarViewModel::class.java)
    }

    private lateinit var binding: FragmentCarBinding

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_car, container, false)

        setupViewModel()

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {
                Navigation.findNavController(view!!)
                    .navigate(CarFragmentDirections.actionCarFragmentToSettingsFragment())
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupViewModel() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        observeViewStatus()
    }

    private fun observeViewStatus() {
        binding.viewModel?.status?.observe(viewLifecycleOwner, Observer { viewStatus ->
            if (viewStatus == ViewStatus.EMPTY_FIELDS)
                Toast.makeText(context, getString(R.string.fields_cannot_be_empty), Toast.LENGTH_SHORT).show()
            else if (viewStatus == ViewStatus.LOCATIONS_ERROR)
                Toast.makeText(context, getString(R.string.incorrect_locations), Toast.LENGTH_SHORT).show()
        })
    }
}
