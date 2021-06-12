package com.midevs.walmartchallenge.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.*


class Converters {
    val gson = Gson()

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

}