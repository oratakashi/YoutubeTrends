package com.oratakashi.youtube.domain.state

import com.oratakashi.youtube.domain.model.main.ItemModel

sealed class MainState {
    object Loading : MainState()

    data class Result(val data : List<ItemModel>) : MainState()
    data class Error(val error : Throwable) : MainState()
}