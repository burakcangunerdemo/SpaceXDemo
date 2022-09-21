package com.example.demospacex.db

import androidx.room.TypeConverter
import com.example.demospacex.models.RocketsItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class MyTypeConverters {

    @TypeConverter
    fun fromDateToLong(date: Date): Long {
        return date.time
    }
    @TypeConverter
    fun fromLongToDate(date: Long): Date {
        return Date(date)
    }
    @TypeConverter
    fun fromClothingToJSON(clothinglist: RocketsItem): String {
        return Gson().toJson(clothinglist)
    }
    @TypeConverter
    fun fromJSONToClothing(json: String): RocketsItem {
        return Gson().fromJson(json,RocketsItem::class.java)
    }
}