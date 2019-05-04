/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.ui.publictransportfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.aib.tricitytravel.R
import com.aib.tricitytravel.data.db.RoomConverters
import com.aib.tricitytravel.data.dto.FavoriteStop
import com.aib.tricitytravel.databinding.FragmentPublicTransportBinding
import com.aib.tricitytravel.ui.BaseFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class PublicTransportFragment : BaseFragment(), PublicTransportRecyclerAdapter.OnStopListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(PublicTransportViewModel::class.java)
    }

    private val stopItems = mutableListOf<FavoriteStop>()

    private lateinit var binding: FragmentPublicTransportBinding
    private lateinit var recyclerAdapter: PublicTransportRecyclerAdapter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_public_transport, container, false)

        setupViewModel()
        setupRecyclerView()

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {
                Navigation.findNavController(view!!)
                    .navigate(PublicTransportFragmentDirections.actionPublicTransportFragmentToSettingsFragment())
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStopClick(position: Int) {
        val stop = stopItems[position]
        Navigation.findNavController(view!!)
            .navigate(
                PublicTransportFragmentDirections.actionPublicTransportFragmentToStopFragment(
                    stop.stopId.toInt(),
                    stop.stopDesc,
                    RoomConverters().toJson(stop.routeIds)
                )
            )
    }

    override fun onStopLongClick(position: Int) {
        val stop = stopItems[position]
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle(getString(R.string.do_you_really_want_to_delete))
                setPositiveButton(
                    R.string.yes
                ) { _, _ ->
                    GlobalScope.launch {
                        viewModel.deleteFavoriteStop(stop)
                    }
                    stopItems.removeAt(position)
                    recyclerAdapter.notifyItemRemoved(position)
                }
                setNegativeButton(R.string.no, null)
            }
            builder.create()
        }
        alertDialog?.show()
    }

    private fun setupViewModel() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        observeStopItems()

        GlobalScope.launch {
            viewModel.getFavoriteStops()
        }
    }

    private fun observeStopItems() {
        viewModel.stopItems.observe(viewLifecycleOwner, Observer { stopItems ->
            this.stopItems.clear()
            this.stopItems.addAll(stopItems)
            recyclerAdapter.notifyDataSetChanged()
        })
    }

    private fun setupRecyclerView() {
        recyclerAdapter = PublicTransportRecyclerAdapter(stopItems, this)
        binding.publicTransportRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerAdapter
        }
    }
}
