/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.ui.settingsfragment.choosekeywordsfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aib.tricitytravel.R
import com.aib.tricitytravel.data.dto.ReportKeyword
import com.aib.tricitytravel.databinding.FragmentSettingsChooseKeywordBinding
import com.google.android.material.chip.Chip
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsChooseKeywordFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SettingsChooseKeywordViewModel::class.java)
    }

    private lateinit var binding: FragmentSettingsChooseKeywordBinding

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings_choose_keyword, container, false)

        setupViewModel()
        setupAddKeywordEditText()

        return binding.root
    }

    private fun setupViewModel() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        observeKeywords()

        GlobalScope.launch {
            viewModel.getKeywords()
        }
    }

    private fun observeKeywords() {
        viewModel.keywords.observe(viewLifecycleOwner, Observer { keywords ->
            binding.chipGroup.removeAllViews()
            keywords.forEach { keyword ->
                val chip = createChip(keyword)
                binding.chipGroup.addView(chip)
            }
        })
    }

    private fun createChip(keyword: ReportKeyword): Chip {
        val chip = layoutInflater.inflate(R.layout.chip_item, binding.chipGroup, false) as Chip
        chip.apply {
            text = keyword.content
            isClickable = false
            setOnCloseIconClickListener {
                GlobalScope.launch {
                    viewModel.deleteKeyword(keyword)
                }
            }
        }
        return chip
    }

    private fun setupAddKeywordEditText() {
        binding.addKeywordEditText.setOnEditorActionListener { view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (view.text.isNotBlank()) {
                    GlobalScope.launch {
                        viewModel.addKeyword(ReportKeyword(0, view.text.toString()))
                    }
                } else {
                    Toast.makeText(context, getString(R.string.fields_cannot_be_empty), Toast.LENGTH_SHORT).show()
                }
                view.text = ""
            }
            true
        }
    }
}
