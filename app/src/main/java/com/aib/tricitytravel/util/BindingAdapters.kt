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