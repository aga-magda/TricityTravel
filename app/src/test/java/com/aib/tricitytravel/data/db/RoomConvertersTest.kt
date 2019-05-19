/*
 * Copyright © 2019 by Agnieszka Maciejewska, Maciej Królik, Krzysztof Mikołajczyk. TricityTravel
 * This application is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.aib.tricitytravel.data.db

import org.junit.Assert
import org.junit.Test

class RoomConvertersTest {

    @Test
    fun toListOfStrings_passJson_returnsListOfStrings() {
        val jsonString = "[\"test1\",\"test2\",\"test3\"]"

        val result = RoomConverters().toListOfStrings(jsonString)

        Assert.assertEquals(result, listOf("test1", "test2", "test3"))
    }

    @Test
    fun toJson_passListOfStrings_returnsJson() {
        val list = listOf("test1", "test2", "test3")

        val result = RoomConverters().toJson(list)

        Assert.assertEquals(result, "[\"test1\",\"test2\",\"test3\"]")
    }
}