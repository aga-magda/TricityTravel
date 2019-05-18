/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.ui.settingsfragment.choosekeywordsfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aib.tricitytravel.data.TrojmiastoRepository
import com.aib.tricitytravel.data.dto.ReportKeyword
import javax.inject.Inject

class SettingsChooseKeywordViewModel @Inject constructor(
    private val repository: TrojmiastoRepository
) : ViewModel() {

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _keywords = MutableLiveData<List<ReportKeyword>>()
    val keywords: LiveData<List<ReportKeyword>> = _keywords

    suspend fun getKeywords() {
        val keywords = repository.getKeywords()
        _keywords.postValue(keywords)
        _isLoading.postValue(false)
    }

    suspend fun addKeyword(keyword: ReportKeyword) {
        repository.addKeyword(keyword)
        val keywords = repository.getKeywords()
        _keywords.postValue(keywords)
    }

    suspend fun deleteKeyword(keyword: ReportKeyword) {
        repository.deleteKeyword(keyword)
        val keywords = repository.getKeywords()
        _keywords.postValue(keywords)
    }
}