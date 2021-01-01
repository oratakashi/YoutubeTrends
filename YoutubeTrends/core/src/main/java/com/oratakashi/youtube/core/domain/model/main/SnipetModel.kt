package com.oratakashi.youtube.core.domain.model.main

data class SnipetModel(
    val publishedAt: String,
    val channelId: String,
    val title: String,
    val description: String,
    val channelTitle: String,
    val categoryId: String,
    val thumnails: ThumnailsModel,
)
