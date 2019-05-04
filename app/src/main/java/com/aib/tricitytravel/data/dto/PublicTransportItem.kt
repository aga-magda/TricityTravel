/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.data.dto

import com.aib.tricitytravel.data.dto.api.Delay
import com.aib.tricitytravel.ui.publictransportfragment.DelayStatus
import com.aib.tricitytravel.util.PublicTransportUtils

data class PublicTransportItem(
    val routeId: String,
    val stopDesc: String,
    val headSign: String,
    val theoreticalTime: String,
    val estimatedTime: String,
    val delayInSeconds: Int,
    val delay: String,
    val delayStatus: DelayStatus
) {
    constructor(
        routeId: String,
        stopDesc: String,
        headSign: String,
        theoreticalTime: String,
        estimatedTime: String,
        delayInSeconds: Int
    ) : this(
        routeId,
        stopDesc,
        headSign,
        theoreticalTime,
        estimatedTime,
        delayInSeconds,
        delay = PublicTransportUtils.calculateDelayFrom(delayInSeconds),
        delayStatus = PublicTransportUtils.getDelayStatusFrom(delayInSeconds)
    )

    constructor(
        delay: Delay,
        stopDesc: String
    ) : this(
        delay.routeId,
        stopDesc,
        delay.headsign,
        delay.theoreticalTime,
        delay.estimatedTime,
        delay.delayInSeconds.toInt()
    )
}