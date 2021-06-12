package com.midevs.walmartchallenge.ui

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.midevs.walmartchallenge.base.BaseViewModel
import com.midevs.walmartchallenge.models.Movie
import com.midevs.walmartchallenge.models.MovieDao
import com.midevs.walmartchallenge.models.PaginatedResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import com.midevs.walmartchallenge.BuildConfig.API_KEY

class MovieListViewModel(private val movieDao: MovieDao) : BaseViewModel() {

    var movies = mutableListOf<Movie>()
    val moviesList: MutableLiveData<List<Movie>> = MutableLiveData()
    var page: Int = 0
    var count: Int = 0
    fun getMovies(page: Int) {
        this.page = page
        isLoading.set(true)
        subscription = baseApi.getMovies(API_KEY, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response -> onGetMoviesSuccess(response) },
                { throwable -> onFailure(throwable) })
    }

    fun nextPage() {
        if (count > (page + 1)) {
            getMovies(page + 1)
        } else {
            // no more pages
            isLoading.set(false)
            moviesList.value = movies
            movies.clear()
        }
    }

    @SuppressLint("CheckResult")
    private fun onGetMoviesSuccess(result: PaginatedResponse<Movie>) {
        if (result.getCount() > 0) {
            count = result.getCount()
            movies.addAll(result.getResults()!!)
            Observable.just(movieDao)
                .subscribeOn(Schedulers.io())
                .subscribe({ it.insertAll(result.getResults()!!) }, { throwable ->
                    onFailure(throwable)
                })
            moviesList.value = movies
            movies.clear()
            isLoading.set(false)
        }
    }

    private fun onFailure(throwable: Throwable) {
        isLoading.set(false)
        if (throwable is HttpException) {
            snackBarMessage.value = "Error reaching our servers - ${throwable.code()}"
        }
    }

    fun isLastPage(): Boolean {
        return page == count
    }
}