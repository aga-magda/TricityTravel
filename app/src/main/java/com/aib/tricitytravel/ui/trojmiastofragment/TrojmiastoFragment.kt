/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.ui.trojmiastofragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.aib.tricitytravel.R
import com.aib.tricitytravel.databinding.FragmentTrojmiastoBinding
import com.aib.tricitytravel.ui.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class TrojmiastoFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(TrojmiastoViewModel::class.java)
    }

    private val news = mutableListOf<Pair<String, String>>()
    private var isFiltered = false

    private lateinit var binding: FragmentTrojmiastoBinding
    private lateinit var recyclerAdapter: TrojmiastoRecyclerAdapter
    private lateinit var sharedPref: SharedPreferences

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_trojmiasto, container, false)
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        isFiltered = sharedPref.getBoolean("prefIsFiltered", false)

        setupViewModel()
        setupRecyclerView()

        setFiltering()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.trojmiasto_report_menu, menu)
        setFilterIcon(menu.findItem(R.id.filter))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {
                Navigation.findNavController(view!!)
                    .navigate(TrojmiastoFragmentDirections.actionTrojmiastoFragmentToSettingsFragment())
                return true
            }
            R.id.filter -> {
                setFiltering()
                setFilterIcon(item)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFiltering() {
        if (isFiltered) {
            isFiltered = false
            sharedPref.edit().putBoolean("prefIsFiltered", true).apply()
            viewModel.getNews()
        } else {
            isFiltered = true
            sharedPref.edit().putBoolean("prefIsFiltered", false).apply()
            viewModel.getFilteredNews()
        }
    }

    private fun setFilterIcon(item: MenuItem) {
        if (!isFiltered) {
            item.setIcon(R.drawable.ic_filter)
        } else {
            item.setIcon(R.drawable.ic_all)
        }
    }

    private fun setupViewModel() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        observeNews()
    }

    private fun observeNews() {
        viewModel.news.observe(viewLifecycleOwner, Observer { news ->
            this.news.clear()
            this.news.addAll(news)
            recyclerAdapter.notifyDataSetChanged()
        })
    }

    private fun setupRecyclerView() {
        recyclerAdapter = TrojmiastoRecyclerAdapter(news)
        binding.trojmiastoRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerAdapter
        }
    }
}
