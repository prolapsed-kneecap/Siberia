package com.charkosoff.siberia

import android.app.Application
import com.charkosoff.siberia.fragment.allPlaces.AllPlacesViewModel
import com.charkosoff.siberia.fragment.mainFrag.MainFragmentViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class AgauApplication : Application() {

    val appModule = module {
        single <Repository> { Repository() }
        viewModel{AllPlacesViewModel(get())}
        viewModel { MainFragmentViewModel(get()) }
    }
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@AgauApplication)
            modules(appModule)
        }
    }
}