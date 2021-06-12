package com.midevs.walmartchallenge.network

import com.midevs.walmartchallenge.models.Genre
import com.midevs.walmartchallenge.models.GenresResponse
import com.midevs.walmartchallenge.models.Movie
import com.midevs.walmartchallenge.models.PaginatedResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.*


interface BaseApi {

    @GET("/3/trending/movie/week")
    fun getMovies(
        @Query("api_key") token: String,
        @Query("page") page: Int
    ): Observable<PaginatedResponse<Movie>>


    @GET("/3/genre/movie/list")
    fun getGenres(@Query("api_key") apiKey: String): Observable<GenresResponse<Genre>>

}