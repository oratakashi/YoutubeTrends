package com.oratakashi.youtube.core.di

import com.oratakashi.youtube.presentation.viewmodel.favorite.all.AllViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object FavoriteModule {
    val viewModelModule = module {
        viewModel { AllViewModel(get()) }
    }
}