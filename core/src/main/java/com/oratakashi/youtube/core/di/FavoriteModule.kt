package com.oratakashi.youtube.core.di

import com.oratakashi.youtube.presentation.viewmodel.favorite.all.AllViewModel
import com.oratakashi.youtube.presentation.viewmodel.favorite.game.GameViewModel
import com.oratakashi.youtube.presentation.viewmodel.favorite.music.MusicViewModel
import com.oratakashi.youtube.presentation.viewmodel.favorite.sport.SportViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object FavoriteModule {
    val viewModelModule = module {
        viewModel { AllViewModel(get()) }
        viewModel { GameViewModel(get()) }
        viewModel { MusicViewModel(get()) }
        viewModel { SportViewModel(get()) }
    }
}