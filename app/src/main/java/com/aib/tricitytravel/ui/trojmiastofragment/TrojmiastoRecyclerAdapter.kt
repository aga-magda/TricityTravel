/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.ui.trojmiastofragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aib.tricitytravel.R
import kotlinx.android.synthetic.main.trojmiasto_item.view.*

class TrojmiastoRecyclerAdapter(
    private val dataSet: List<Pair<String, String>>
) : RecyclerView.Adapter<TrojmiastoRecyclerAdapter.TrojmiastoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrojmiastoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.trojmiasto_item, parent, false)
        return TrojmiastoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TrojmiastoViewHolder, position: Int) {
        holder.timeTextView?.text = dataSet[position].first
        holder.titleTextView?.text = dataSet[position].second
    }

    override fun getItemCount() = dataSet.size

    class TrojmiastoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var timeTextView: TextView? = null
        var titleTextView: TextView? = null

        init {
            timeTextView = itemView.trojmiastoTimeText
            titleTextView = itemView.trojmiastoTitleText
        }
    }
}