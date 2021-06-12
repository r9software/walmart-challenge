package com.midevs.walmartchallenge.ui.movies

import android.annotation.SuppressLint
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.midevs.walmartchallenge.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import com.midevs.walmartchallenge.BuildConfig.API_KEY
import com.midevs.walmartchallenge.models.*

class MovieDetailViewModel(private val movieDao: MovieDao) :
    BaseViewModel() {

    val movie: ObservableField<Movie> = ObservableField()
    val mutableMovie: MutableLiveData<Movie> = MutableLiveData()

    fun getMovie(id: Int) {
        isLoading.set(true)
        subscription = baseApi.getMovie(id, API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response -> onGetMovieSuccess(response) },
                { throwable -> onFailure(throwable) })
    }

    @SuppressLint("CheckResult")
    private fun onGetMovieSuccess(result: Movie) {
        movie.set(result)
        mutableMovie.value = result
        Observable.just(movieDao)
            .subscribeOn(Schedulers.io())
            .subscribe({ it.insert(result) }, { throwable ->
                onFailure(throwable)
            })
        isLoading.set(false)
    }

    private fun onFailure(throwable: Throwable) {
        isLoading.set(false)
        if (throwable is HttpException) {
            snackBarMessage.value = "Error reaching our servers - ${throwable.code()}"
        }
    }

}