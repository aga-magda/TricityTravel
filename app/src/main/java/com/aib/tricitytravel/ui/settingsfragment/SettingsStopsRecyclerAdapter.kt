package com.aib.tricitytravel.ui.settingsfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aib.tricitytravel.R
import com.aib.tricitytravel.data.dto.StopItem
import kotlinx.android.synthetic.main.public_transport_item.view.*

class SettingsStopsRecyclerAdapter(
    private val dataSet: List<StopItem>
) : RecyclerView.Adapter<SettingsStopsRecyclerAdapter.SettingsStopsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsStopsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.settings_stop_item, parent, false)
        return SettingsStopsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SettingsStopsViewHolder, position: Int) {
        holder.routeIdTextView?.text = dataSet[position].routeId
        holder.stopDescTextView?.text = dataSet[position].stopDesc
        holder.headSignTextView?.text = dataSet[position].headSign
    }

    override fun getItemCount() = dataSet.size

    class SettingsStopsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var routeIdTextView: TextView? = null
        var stopDescTextView: TextView? = null
        var headSignTextView: TextView? = null

        init {
            routeIdTextView = itemView.routeIdText
            stopDescTextView = itemView.stopDescText
            headSignTextView = itemView.headSignText
        }
    }
}