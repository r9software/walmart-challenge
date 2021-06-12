package com.midevs.walmartchallenge.models

import androidx.room.Dao
import androidx.room.Query
import com.midevs.walmartchallenge.base.BaseDao
import io.reactivex.Single

@Dao
interface MovieDao : BaseDao<Movie> {

    @Query("DELETE FROM movie_table")
    fun deleteAll()

    @Query("SELECT * FROM movie_table WHERE id = :id")
    fun getMovieById(id: Int): Single<Movie>
}