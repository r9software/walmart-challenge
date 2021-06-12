package com.midevs.walmartchallenge.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre_table")
data class Genre(
    @PrimaryKey val id: Int, val name: String
)