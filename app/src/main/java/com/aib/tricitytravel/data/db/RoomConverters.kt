package com.aib.tricitytravel.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomConverters {

    @TypeConverter
    fun toListOfStrings(json: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun toJson(list: List<String>): String {
        return Gson().toJson(list)
    }
}