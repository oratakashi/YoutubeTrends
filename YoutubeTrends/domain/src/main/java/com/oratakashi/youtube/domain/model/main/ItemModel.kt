package com.oratakashi.youtube.domain.model.main

import com.oratakashi.youtube.domain.magic.Map

data class ItemModel(
    @Map("id")
    val id: String,
    val snipet: SnipetModel,
    val statistics: StatisticsModel
)