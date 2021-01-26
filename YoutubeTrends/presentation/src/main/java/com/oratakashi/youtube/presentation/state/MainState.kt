package com.oratakashi.youtube.presentation.state

import com.oratakashi.youtube.presentation.model.main.Items

sealed class MainState {
    object Loading : MainState()

    data class Result(val data: List<Items>) : MainState()
    data class Error(val error: Throwable) : MainState()
}
