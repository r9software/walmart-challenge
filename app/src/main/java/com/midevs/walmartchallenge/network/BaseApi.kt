package com.midevs.walmartchallenge.network

import com.midevs.walmartchallenge.models.*
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

    @GET("/3/movie/{id}")
    fun getMovie(@Path("id") id: Int, @Query("api_key") apiKey: String): Observable<Movie>


}