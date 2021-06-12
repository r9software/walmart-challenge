package com.midevs.walmartchallenge.models

import android.net.UrlQuerySanitizer
import com.squareup.moshi.Json


public class PaginatedResponse<T> {

    @Json(name = "total_pages")
    private val total_pages: Int = 0


    @Json(name = "results")
    private var results: List<T>? = null

    fun getCount(): Int {
        return total_pages
    }

    fun getResults(): List<T>? {
        return results
    }
}
