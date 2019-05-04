/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.ui.publictransportfragment

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aib.tricitytravel.R
import com.aib.tricitytravel.data.dto.PublicTransportItem
import com.aib.tricitytravel.util.PublicTransportUtils
import kotlinx.android.synthetic.main.stop_item.view.*

class StopRecyclerAdapter(
    private val dataSet: List<PublicTransportItem>
) : RecyclerView.Adapter<StopRecyclerAdapter.StopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StopViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.stop_item, parent, false)
        return StopViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StopViewHolder, position: Int) {
        holder.vehicleTypeImageView?.setImageResource(PublicTransportUtils.getImageResource(dataSet[position].routeId))
        holder.routeIdTextView?.text = dataSet[position].routeId
        holder.stopDescTextView?.text = dataSet[position].stopDesc
        holder.headSignTextView?.text = dataSet[position].headSign
        holder.theoreticalTimeTextView?.text = dataSet[position].theoreticalTime
        holder.delayTextView?.text = dataSet[position].delay
        holder.estimatedTimesTextView?.text = dataSet[position].estimatedTime
        holder.delayStatusTextView?.text = PublicTransportUtils.getDelayStatusDescription(dataSet[position].delayStatus)

        when (dataSet[position].delayStatus) {
            DelayStatus.AHEAD_OF_TIME -> holder.delayStatusTextView?.setTextColor(Color.rgb(255, 165, 0))
            DelayStatus.ON_TIME -> holder.delayStatusTextView?.setTextColor(Color.GREEN)
            DelayStatus.DELAYED -> holder.delayStatusTextView?.setTextColor(Color.RED)
        }
    }

    override fun getItemCount() = dataSet.size

    class StopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var vehicleTypeImageView: ImageView? = null
        var routeIdTextView: TextView? = null
        var stopDescTextView: TextView? = null
        var headSignTextView: TextView? = null
        var theoreticalTimeTextView: TextView? = null
        var delayTextView: TextView? = null
        var estimatedTimesTextView: TextView? = null
        var delayStatusTextView: TextView? = null

        init {
            vehicleTypeImageView = itemView.vehicleTypeImage
            routeIdTextView = itemView.routeIdsText
            stopDescTextView = itemView.stopDescText
            headSignTextView = itemView.directionsText
            theoreticalTimeTextView = itemView.theoreticalTimeText
            delayTextView = itemView.delayText
            estimatedTimesTextView = itemView.estimatedTimeText
            delayStatusTextView = itemView.delayStatusText
        }
    }
}