package com.midevs.walmartchallenge.models

import android.net.UrlQuerySanitizer
import com.squareup.moshi.Json


public class GenresResponse<Genre> {

    @Json(name = "genres")
    private var results: List<Genre>? = null


    fun getResults(): List<Genre>? {
        return results
    }
}
