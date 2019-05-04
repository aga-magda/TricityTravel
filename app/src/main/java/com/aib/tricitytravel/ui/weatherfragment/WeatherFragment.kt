/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.ui.weatherfragment

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aib.tricitytravel.R
import com.aib.tricitytravel.databinding.FragmentWeatherBinding
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(WeatherViewModel::class.java)
    }

    private var city = ""

    private lateinit var binding: FragmentWeatherBinding

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false)

        getCityFromSharedPref()
        setupViewModel()

        return binding.root
    }

    private fun setupViewModel() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        GlobalScope.launch {
            viewModel.getWeather(city)
        }
    }

    private fun getCityFromSharedPref() {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        city = sharedPref?.getString("prefWeatherCity", "Gdańsk")!!
    }
}