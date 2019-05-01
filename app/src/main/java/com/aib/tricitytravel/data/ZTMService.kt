package com.aib.tricitytravel.data

import com.aib.tricitytravel.data.dto.api.EstimatedTimes
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ZTMService {

    @GET("delays")
    fun getEstimatedTimes(@Query("stopId") stopId: String): Deferred<EstimatedTimes>
}