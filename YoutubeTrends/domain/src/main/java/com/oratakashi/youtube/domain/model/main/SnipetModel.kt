package com.oratakashi.youtube.domain.model.main

import com.oratakashi.youtube.domain.magic.Map

data class SnipetModel(
    @Map("publishedAt")
    val publishedAt: String,
    @Map("channelId")
    val channelId: String,
    @Map("title")
    val title: String,
    @Map("description")
    val description: String,
    @Map("channelTitle")
    val channelTitle: String,
    @Map("categoryId")
    val categoryId: String,
    val thumnails: ThumnailsModel,
)
