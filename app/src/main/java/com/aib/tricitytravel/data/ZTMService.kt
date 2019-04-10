package com.aib.tricitytravel.data

import com.aib.tricitytravel.data.dto.EstimatedTimes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ZTMService {

    @GET("delays")
    fun getEstimatedTimes(@Query("stopId") stopId: String): Call<EstimatedTimes>
}