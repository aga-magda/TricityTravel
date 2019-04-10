package com.aib.tricitytravel.ui.settingsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aib.tricitytravel.R
import com.aib.tricitytravel.data.dto.StopItem

class SettingsFragment : Fragment() {

    private lateinit var settingsStopsRecyclerView: RecyclerView
    private lateinit var recyclerAdapter: SettingsStopsRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)

        settingsStopsRecyclerView = rootView.findViewById(R.id.settingsStopsRecyclerView)

        val dataSet = listOf(
            StopItem("Dworzec Główny", "Jeleniogórska", "118"),
            StopItem("Sobótki", "Jaworzniaków", "262")
        )

        recyclerAdapter = SettingsStopsRecyclerAdapter(dataSet)
        settingsStopsRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerAdapter
        }

        return rootView
    }
}
