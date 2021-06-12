package com.midevs.walmartchallenge.models

import android.net.UrlQuerySanitizer
import androidx.room.TypeConverters
import com.midevs.walmartchallenge.utils.Converters
import com.squareup.moshi.Json


public class GenresResponse<Genre> {

    @TypeConverters(Converters::class)
    @field:Json(name = "genres")
    private var results: List<Genre>? = null


    fun getResults(): List<Genre>? {
        return results
    }
}
