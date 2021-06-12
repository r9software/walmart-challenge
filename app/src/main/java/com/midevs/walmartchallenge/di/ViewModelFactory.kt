package com.midevs.walmartchallenge.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.midevs.walmartchallenge.base.BaseDatabase
import com.midevs.walmartchallenge.ui.MovieListViewModel


class ViewModelFactory(private val activity: FragmentActivity?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieListViewModel(
                (BaseDatabase.getAppDataBase(activity!!.applicationContext)!!.movieDao())
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}