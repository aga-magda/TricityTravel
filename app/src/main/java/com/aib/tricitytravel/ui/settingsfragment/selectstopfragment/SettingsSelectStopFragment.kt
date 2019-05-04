package com.aib.tricitytravel.ui.settingsfragment.selectstopfragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aib.tricitytravel.R
import com.aib.tricitytravel.data.dto.FavoriteStop
import com.aib.tricitytravel.data.dto.api.Stop
import com.aib.tricitytravel.databinding.FragmentSettingsSelectStopBinding
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsSelectStopFragment : Fragment(), SettingsSelectStopRecyclerAdapter.OnStopListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SettingsSelectStopViewModel::class.java)
    }
    private val stopItems = mutableListOf<Stop>()
    private val stopItemsFull = mutableListOf<Stop>()

    private lateinit var binding: FragmentSettingsSelectStopBinding
    private lateinit var recyclerAdapter: SettingsSelectStopRecyclerAdapter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings_select_stop, container, false)

        setupViewModel()
        setupRecyclerView()
        setupSearchStopEditText()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.select_stop_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh_stops -> {
                GlobalScope.launch {
                    viewModel.refreshStops()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStopClick(position: Int) {
        openDialog(position)
    }

    private fun setupViewModel() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        observeStopItems()

        GlobalScope.launch {
            viewModel.getStops()
        }
    }

    private fun observeStopItems() {
        viewModel.stopItems.observe(viewLifecycleOwner, Observer { stopItems ->
            this.stopItems.clear()
            this.stopItems.addAll(stopItems)
            this.stopItemsFull.clear()
            this.stopItemsFull.addAll(stopItems)
            recyclerAdapter.notifyDataSetChanged()
        })
    }

    private fun setupRecyclerView() {
        recyclerAdapter = SettingsSelectStopRecyclerAdapter(stopItems, stopItemsFull, this)
        binding.selectStopRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerAdapter
        }
    }

    private fun setupSearchStopEditText() {
        binding.selectStopEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                recyclerAdapter.filter.filter(text)
            }
        })
    }

    private fun openDialog(adapterPosition: Int) {
        val selectedItems = ArrayList<Int>()
        val alertDialog: AlertDialog = requireActivity().let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle(R.string.choose_bus_number)
                setMultiChoiceItems(
                    stopItems[adapterPosition].routeIds.toTypedArray(), null
                ) { _, which, isChecked ->
                    if (isChecked) {
                        selectedItems.add(which)
                    } else if (selectedItems.contains(which)) {
                        selectedItems.remove(Integer.valueOf(which))
                    }
                }
                setPositiveButton(
                    R.string.save
                ) { _, _ ->
                    val selectedRouteIds = mutableListOf<String>()
                    val selectedDirections = mutableListOf<String>()
                    selectedItems.forEach { item ->
                        selectedRouteIds.add(stopItems[adapterPosition].routeIds[item])
                        selectedDirections.add(stopItems[adapterPosition].directions[item])
                    }

                    val favoriteStop = FavoriteStop(stopItems[adapterPosition])
                    favoriteStop.routeIds = selectedRouteIds
                    favoriteStop.directions = selectedDirections
                    GlobalScope.launch {
                        viewModel.addFavoriteStop(favoriteStop)
                    }
                }
            }
            builder.create()
        }
        alertDialog.show()
    }
}
