package com.aib.tricitytravel.util

import com.aib.tricitytravel.R
import com.aib.tricitytravel.TricityTravel
import com.aib.tricitytravel.ui.publictransportfragment.DelayStatus
import java.util.concurrent.TimeUnit

class PublicTransportUtils {

    companion object {

        fun getDelayStatusFrom(delayInSeconds: Int): DelayStatus {
            return when (delayInSeconds) {
                in Int.MIN_VALUE..-2 -> DelayStatus.AHEAD_OF_TIME
                in -1..1 -> DelayStatus.ON_TIME
                in 1..Int.MAX_VALUE -> DelayStatus.DELAYED
                else -> throw IndexOutOfBoundsException("Delay is out of range.")
            }
        }

        fun getDelayStatusDescription(delayStatus: DelayStatus): String {
            return when (delayStatus) {
                DelayStatus.AHEAD_OF_TIME -> TricityTravel.res?.getString(R.string.ahead_of_time)!!
                DelayStatus.ON_TIME -> TricityTravel.res?.getString(R.string.on_time)!!
                DelayStatus.DELAYED -> TricityTravel.res?.getString(R.string.delayed)!!
            }
        }

        fun calculateDelayFrom(delayInSeconds: Int): String {
            return "${TimeUnit.SECONDS.toMinutes(delayInSeconds.toLong())} min"
        }
    }
}