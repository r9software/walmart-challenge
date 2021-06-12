package com.midevs.walmartchallenge.models

import androidx.room.Dao
import androidx.room.Query
import com.midevs.walmartchallenge.base.BaseDao
import io.reactivex.Single

@Dao
interface GenresDao : BaseDao<Genre> {

    @Query("DELETE FROM genre_table")
    fun deleteAll()

    @Query("SELECT * FROM genre_table WHERE id = :id")
    fun getGenreById(id: Int): Single<Genre>
}