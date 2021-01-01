package com.oratakashi.youtube.di

import com.oratakashi.youtube.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationModule {
    val viewModelModule = module {
        viewModel {
            HomeViewModel(get())
        }
    }
}