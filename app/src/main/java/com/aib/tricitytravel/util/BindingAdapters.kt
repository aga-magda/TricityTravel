/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.util

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("android:isLoadingVisible")
fun isLoadingVisible(view: View, isLoading: Boolean) {
    if (isLoading)
        view.visibility = View.VISIBLE
    else
        view.visibility = View.GONE
}

@BindingAdapter("android:isLoadingGone")
fun isLoadingGone(view: View, isLoading: Boolean) {
    if (isLoading)
        view.visibility = View.GONE
    else
        view.visibility = View.VISIBLE
}

@BindingAdapter("android:showIfZero", "android:isLoading")
fun showIfZero(view: View, collection: Collection<*>?, isLoading: Boolean) {
    if (!isLoading) {
        if (collection.isNullOrEmpty())
            view.visibility = View.VISIBLE
        else
            view.visibility = View.GONE
    }
}