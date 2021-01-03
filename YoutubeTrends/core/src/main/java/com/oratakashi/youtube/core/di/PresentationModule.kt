package com.oratakashi.youtube.core.di

import com.oratakashi.youtube.presentation.viewmodel.game.GameViewModel
import com.oratakashi.youtube.presentation.viewmodel.home.HomeViewModel
import com.oratakashi.youtube.presentation.viewmodel.music.MusicViewModel
import com.oratakashi.youtube.presentation.viewmodel.sport.SportViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationModule {
    val viewModelModule = module {
        viewModel { HomeViewModel(get()) }
        viewModel { GameViewModel(get()) }
        viewModel { MusicViewModel(get()) }
        viewModel { SportViewModel(get()) }
    }
}