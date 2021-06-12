package com.midevs.walmartchallenge.di.component

import com.midevs.walmartchallenge.base.BaseViewModel
import com.midevs.walmartchallenge.base.BaseActivity
import com.midevs.walmartchallenge.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(baseViewModel: BaseViewModel)
    fun inject(baseActivity: BaseActivity)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}