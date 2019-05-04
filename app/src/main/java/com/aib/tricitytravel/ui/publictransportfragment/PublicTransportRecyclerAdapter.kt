package com.aib.tricitytravel.ui.publictransportfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aib.tricitytravel.R
import com.aib.tricitytravel.data.dto.FavoriteStop
import kotlinx.android.synthetic.main.stop_list_item.view.*

class PublicTransportRecyclerAdapter(
    private val dataSet: MutableList<FavoriteStop>,
    private val onStopListener: OnStopListener
) : RecyclerView.Adapter<PublicTransportRecyclerAdapter.PublicTransportViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicTransportViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.stop_list_item, parent, false)
        return PublicTransportViewHolder(itemView, onStopListener)
    }

    override fun onBindViewHolder(holder: PublicTransportViewHolder, position: Int) {
        holder.stopDescTextView?.text = dataSet[position].stopDesc
        holder.directionsTextView?.text = dataSet[position].directions.joinToString()
        holder.routeIdsTextView?.text = dataSet[position].routeIds.joinToString()
    }

    override fun getItemCount() = dataSet.size

    class PublicTransportViewHolder(itemView: View, onStopListener: OnStopListener) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener, View.OnLongClickListener {

        var stopDescTextView: TextView? = null
        var directionsTextView: TextView? = null
        var routeIdsTextView: TextView? = null

        var onStopListener: OnStopListener? = null

        init {
            stopDescTextView = itemView.stopDescText
            directionsTextView = itemView.directionsText
            routeIdsTextView = itemView.routeIdsText

            this.onStopListener = onStopListener
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            onStopListener?.onStopClick(adapterPosition)
        }

        override fun onLongClick(v: View?): Boolean {
            onStopListener?.onStopLongClick(adapterPosition)
            return true
        }
    }

    interface OnStopListener {
        fun onStopClick(position: Int)
        fun onStopLongClick(position: Int)
    }
}