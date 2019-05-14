/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.util

import com.aib.tricitytravel.R
import com.aib.tricitytravel.ui.publictransportfragment.DelayStatus
import org.junit.Assert
import org.junit.Test

class PublicTransportUtilsTest {

    @Test
    fun getDelayStatusFrom_delayEquals60Seconds_returnsOnTime() {
        val delayStatus = PublicTransportUtils.getDelayStatusFrom(60)
        Assert.assertEquals(DelayStatus.ON_TIME, delayStatus)
    }

    @Test
    fun getDelayStatusFrom_delayEqualsMinus500Seconds_returnsAheadOfTime() {
        val delayStatus = PublicTransportUtils.getDelayStatusFrom(-500)
        Assert.assertEquals(DelayStatus.AHEAD_OF_TIME, delayStatus)
    }

    @Test
    fun getDelayStatusFrom_delayEquals61Seconds_returnsDelayed() {
        val delayStatus = PublicTransportUtils.getDelayStatusFrom(61)
        Assert.assertEquals(DelayStatus.DELAYED, delayStatus)
    }

    @Test
    fun calculateDelayFrom_delayInSecondsEquals130_returnsFormattedString() {
        val formattedString = PublicTransportUtils.calculateDelayFrom(130)
        Assert.assertEquals("2 min 10 s", formattedString)
    }

    @Test
    fun getImageResource_routeIdEquals111_returnsBusDrawable() {
        val drawable = PublicTransportUtils.getImageResource("111")
        Assert.assertEquals(R.drawable.ic_bus, drawable)
    }

    @Test
    fun getImageResource_routeIdEquals11_returnsTramDrawable() {
        val drawable = PublicTransportUtils.getImageResource("11")
        Assert.assertEquals(R.drawable.ic_tram, drawable)
    }
}