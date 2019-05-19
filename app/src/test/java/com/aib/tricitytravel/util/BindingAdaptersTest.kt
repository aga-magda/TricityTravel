/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.util

import android.view.View
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class BindingAdaptersTest {

    private lateinit var view: View

    @Before
    fun setUp() {
        view = Mockito.mock(View::class.java)
    }

    @Test
    fun isLoadingVisible_loading_setPassedViewToVisible() {
        isLoadingVisible(view, true)
        Mockito.verify(view).visibility = View.VISIBLE
    }

    @Test
    fun isLoadingVisible_notLoading_setPassedViewToGone() {
        isLoadingVisible(view, false)
        Mockito.verify(view).visibility = View.GONE
    }

    @Test
    fun isLoadingGone_loading_setPassedViewToGone() {
        isLoadingGone(view, true)
        Mockito.verify(view).visibility = View.GONE
    }

    @Test
    fun isLoadingGone_notLoading_setPassedViewToVisible() {
        isLoadingGone(view, false)
        Mockito.verify(view).visibility = View.VISIBLE
    }

    @Test
    fun showIfZero_notLoadingAndNotEmptyCollection_setPassedViewToVisible() {
        showIfZero(view, listOf("", "", ""), false)
        Mockito.verify(view).visibility = View.GONE
    }

    @Test
    fun showIfZero_notLoadingAndEmptyCollection_setPassedViewToVisible() {
        showIfZero(view, listOf<String>(), false)
        Mockito.verify(view).visibility = View.VISIBLE
    }
}