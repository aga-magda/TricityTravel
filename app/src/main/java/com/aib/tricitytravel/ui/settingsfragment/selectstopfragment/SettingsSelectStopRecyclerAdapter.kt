package com.aib.tricitytravel.ui.settingsfragment.selectstopfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aib.tricitytravel.R
import com.aib.tricitytravel.data.dto.api.Stop
import kotlinx.android.synthetic.main.public_transport_item.view.*

class SettingsSelectStopRecyclerAdapter(
    private val dataSet: MutableList<Stop>,
    private val dataSetFull: List<Stop>
) : RecyclerView.Adapter<SettingsSelectStopRecyclerAdapter.SettingsStopsViewHolder>(), Filterable {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsStopsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.settings_stop_item, parent, false)
        return SettingsStopsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SettingsStopsViewHolder, position: Int) {
        holder.stopDescTextView?.text = dataSet[position].stopDesc
        holder.directionsTextView?.text = dataSet[position].directions.joinToString()
        holder.routeIdsTextView?.text = dataSet[position].routeIds.joinToString()
    }

    override fun getItemCount() = dataSet.size

    override fun getFilter(): Filter {
        return stopFilter
    }

    private val stopFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = mutableListOf<Stop>()

            if (constraint.isNullOrEmpty()) {
                filteredList.addAll(dataSetFull)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim()

                for (item in dataSetFull) {
                    if (item.stopDesc.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }

            val results = FilterResults()
            results.values = filteredList

            return results
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            dataSet.clear()
            dataSet.addAll(results?.values as Collection<Stop>)
            notifyDataSetChanged()
        }
    }

    class SettingsStopsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var stopDescTextView: TextView? = null
        var directionsTextView: TextView? = null
        var routeIdsTextView: TextView? = null

        init {
            stopDescTextView = itemView.stopDescText
            directionsTextView = itemView.directionsText
            routeIdsTextView = itemView.routeIdsText
        }
    }
}