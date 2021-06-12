package com.midevs.walmartchallenge.base

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.midevs.walmartchallenge.di.component.DaggerViewModelInjector
import com.midevs.walmartchallenge.di.component.ViewModelInjector
import com.midevs.walmartchallenge.di.module.NetworkModule
import com.midevs.walmartchallenge.network.BaseApi
import io.reactivex.disposables.Disposable
import retrofit2.Retrofit
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var baseApi: BaseApi

    @Inject
    lateinit var retrofit: Retrofit

    protected lateinit var subscription: Disposable

    var isLoading = ObservableField(false)
    val snackBarMessage: MutableLiveData<String> = MutableLiveData()

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        injector.inject(this)
    }

    override fun onCleared() {
        super.onCleared()
        if (::subscription.isInitialized) subscription.dispose()
    }
}