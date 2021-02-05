package com.oratakashi.youtube.domain.state

import com.oratakashi.youtube.domain.model.main.ItemModel

sealed class DomainMainState {
    object Loading : DomainMainState()

    data class Result(val data: List<ItemModel>) : DomainMainState()
    data class Error(val error: Throwable) : DomainMainState()
}