package com.aib.tricitytravel.ui.publictransportfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aib.tricitytravel.R
import com.aib.tricitytravel.data.dto.PublicTransportItem
import com.aib.tricitytravel.ui.BaseFragment

class PublicTransportFragment : BaseFragment() {

    private lateinit var publicTransportRecyclerView: RecyclerView
    private lateinit var recyclerAdapter: PublicTransportRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_public_transport, container, false)

        publicTransportRecyclerView = rootView.findViewById(R.id.publicTransportRecyclerView)

        val dataSet = listOf(
            PublicTransportItem("118", "Dworzec Główny", "Jeleniogórska", "12:30", "12:40", 120),
            PublicTransportItem("262", "Sobótki", "Jaworzniaków", "15:30", "15:40", 185)
        )

        recyclerAdapter = PublicTransportRecyclerAdapter(dataSet)
        publicTransportRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerAdapter
        }

//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://87.98.237.99:88")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()

//        val ztmService = retrofit.create(ZTMService::class.java)
//        ztmService.getEstimatedTimes("1015").enqueue(object : Callback<EstimatedTimes> {
//            override fun onResponse(call: Call<EstimatedTimes>, response: Response<EstimatedTimes>) {
//                val estimatedTimes = response.body()
//
//                val delays = StringBuilder()
//                estimatedTimes?.delay?.forEach { delay ->
//                    delays.append(delay.routeId, " - ", delay.headsign, " - ", delay.estimatedTime, "\n")
//                }
//
//                stopDescriptionText.text = "TEST"
//                stopDelaysText.text = delays.toJson()
//            }
//
//            override fun onFailure(call: Call<EstimatedTimes>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })

        return rootView
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {
                Navigation.findNavController(view!!).navigate(R.id.action_publicTransportFragment_to_settingsFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
