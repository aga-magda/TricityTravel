/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.ui.publictransportfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aib.tricitytravel.R
import com.aib.tricitytravel.data.dto.PublicTransportItem
import com.aib.tricitytravel.databinding.FragmentStopBinding
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class StopFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(StopViewModel::class.java)
    }

    private val publicTransportItems = mutableListOf<PublicTransportItem>()

    private lateinit var binding: FragmentStopBinding
    private lateinit var recyclerAdapter: StopRecyclerAdapter
    private lateinit var args: StopFragmentArgs

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_stop, container, false)

        args = StopFragmentArgs.fromBundle(arguments!!)

        setupViewModel()
        setupRecyclerView()
        setupRefreshLayout()

        return binding.root
    }

    private fun setupViewModel() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        observePublicTransportItems()

        GlobalScope.launch {
            viewModel.getPublicTransportItems(args.stopId, args.stopDesc, args.routeIds)
        }
    }

    private fun observePublicTransportItems() {
        viewModel.publicTransportItems.observe(viewLifecycleOwner, Observer { publicTransportItems ->
            this.publicTransportItems.clear()
            this.publicTransportItems.addAll(publicTransportItems)
            recyclerAdapter.notifyDataSetChanged()
        })
    }

    private fun setupRecyclerView() {
        recyclerAdapter = StopRecyclerAdapter(publicTransportItems)
        binding.stopRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerAdapter
        }
    }

    private fun setupRefreshLayout() {
        binding.stopSwipeRefreshLayout.setOnRefreshListener {
            GlobalScope.launch {
                viewModel.refreshPublicTransportItems(args.stopId, args.stopDesc, args.routeIds)
            }
        }
    }
}
