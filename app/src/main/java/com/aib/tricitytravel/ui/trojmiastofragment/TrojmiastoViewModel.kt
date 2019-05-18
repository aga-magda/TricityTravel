/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.ui.trojmiastofragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aib.tricitytravel.data.TrojmiastoRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class TrojmiastoViewModel @Inject constructor(
    private val repository: TrojmiastoRepository
) : ViewModel() {

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _news = MutableLiveData<List<Pair<String, String>>>()
    val news: LiveData<List<Pair<String, String>>> = _news

    private var _allNews = listOf<Pair<String, String>>()

    fun getNews() {
        GlobalScope.launch {
            _isLoading.postValue(true)
            val news = getCachedNews()
            _news.postValue(news)
            _isLoading.postValue(false)
        }
    }

    fun getFilteredNews() {
        GlobalScope.launch {
            _isLoading.postValue(true)

            val news = getCachedNews()
            val keywords = repository.getKeywords()

            val filteredNews =
                news.filter { newsItem ->
                    keywords.any {
                        newsItem.second.toLowerCase().contains(it.content.toLowerCase())
                    }
                }

            _news.postValue(filteredNews)
            _isLoading.postValue(false)
        }
    }

    private fun getCachedNews(): List<Pair<String, String>> {
        return if (_allNews.isNullOrEmpty()) {
            _allNews = repository.getNewsFromTrojmiastoReport()
            _allNews
        } else {
            _allNews
        }
    }
}