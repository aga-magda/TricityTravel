package com.aib.tricitytravel.ui.publictransportfragment

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aib.tricitytravel.R
import com.aib.tricitytravel.data.dto.PublicTransportItem
import com.aib.tricitytravel.util.PublicTransportUtils
import kotlinx.android.synthetic.main.public_transport_item.view.*

class PublicTransportRecyclerAdapter(
    private val dataSet: List<PublicTransportItem>
) : RecyclerView.Adapter<PublicTransportRecyclerAdapter.PublicTransportViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicTransportViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.public_transport_item, parent, false)
        return PublicTransportViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PublicTransportViewHolder, position: Int) {
        holder.routeIdTextView?.text = dataSet[position].routeId
        holder.stopDescTextView?.text = dataSet[position].stopDesc
        holder.headSignTextView?.text = dataSet[position].headSign
        holder.theoreticalTimeTextView?.text = dataSet[position].theoreticalTime
        holder.delayTextView?.text = dataSet[position].delay
        holder.estimatedTimesTextView?.text = dataSet[position].estimatedTime
        holder.delayStatusTextView?.text = PublicTransportUtils.getDelayStatusDescription(dataSet[position].delayStatus)

        when (dataSet[position].delayStatus) {
            DelayStatus.AHEAD_OF_TIME -> holder.delayStatusTextView?.setTextColor(Color.YELLOW)
            DelayStatus.ON_TIME -> holder.delayStatusTextView?.setTextColor(Color.GREEN)
            DelayStatus.DELAYED -> holder.delayStatusTextView?.setTextColor(Color.RED)
        }
    }

    override fun getItemCount() = dataSet.size

    class PublicTransportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var routeIdTextView: TextView? = null
        var stopDescTextView: TextView? = null
        var headSignTextView: TextView? = null
        var theoreticalTimeTextView: TextView? = null
        var delayTextView: TextView? = null
        var estimatedTimesTextView: TextView? = null
        var delayStatusTextView: TextView? = null

        init {
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