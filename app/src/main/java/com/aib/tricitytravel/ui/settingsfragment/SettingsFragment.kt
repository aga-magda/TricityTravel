package com.aib.tricitytravel.ui.settingsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.aib.tricitytravel.R
import com.aib.tricitytravel.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(SettingsViewModel::class.java)
    }

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)

        setupViewModel()
        setupButtonOnClickListener()

        return binding.root
    }

    private fun setupViewModel() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun setupButtonOnClickListener() {
        binding.settingsAddStopButton.setOnClickListener {
            Navigation.findNavController(view!!).navigate(R.id.action_settingsFragment_to_settingsSelectStopFragment)
        }
    }

//    private fun setupRecyclerView() {
//        val dataSet = listOf(
//            StopItem("Dworzec Główny", "Jeleniogórska", "118"),
//            StopItem("Sobótki", "Jaworzniaków", "262")
//        )
//
//        recyclerAdapter = SettingsStopsRecyclerAdapter(dataSet)
//        binding.settingsStopsRecyclerView.apply {
//            setHasFixedSize(true)
//            layoutManager = LinearLayoutManager(context)
//            adapter = recyclerAdapter
//        }
//    }
}
