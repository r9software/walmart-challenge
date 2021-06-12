package com.midevs.walmartchallenge.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class Movie(
    @PrimaryKey val id: Int, val title: String,
    val poster_path: String, val overview: String,
    val vote_average: Float, val release_date: String, val vot_count: Int,
    val original_language: String, val original_title: String
)