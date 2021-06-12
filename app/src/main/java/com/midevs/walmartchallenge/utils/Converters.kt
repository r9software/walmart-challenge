package com.midevs.walmartchallenge.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.midevs.walmartchallenge.models.Genre
import java.lang.reflect.Type
import java.time.DayOfWeek
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

    @TypeConverter
    fun fromGenres(list: List<Int>?): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toGenres(value: String?): List<Int>? {
        val listType: Type = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromGenresArray(list: List<Genre>?): String {
        return Gson().toJson(list)
    }


    @TypeConverter
    fun jsonToGenres(json: String?): List<Genre>? {
        val listType: Type = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson(json, listType)
    }

    @TypeConverter
    fun GenreToJson(genre: Genre?): String? {
        return gson.toJson(genre)
    }


    @TypeConverter
    fun jsonToGenre(json: String?): Genre? {
        return gson.fromJson(json, Genre::class.java)
    }


}