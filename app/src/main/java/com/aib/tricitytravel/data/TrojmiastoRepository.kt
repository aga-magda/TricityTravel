/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.data

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrojmiastoRepository @Inject constructor() {

    fun getNewsFromTrojmiastoReport(): List<Pair<String, String>> {
        var doc: Document? = null
        try {
            doc = Jsoup.connect(URLs.TROJMIASTO_REPORT).get()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        val titles = doc?.select(".time")!!.eachText()
        val times = doc.select(".report-content-inner h3")!!.eachText()

        return titles.zip(times)
    }
}