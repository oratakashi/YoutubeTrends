package com.oratakashi.youtube.data.model.main

import com.google.gson.annotations.SerializedName
import com.oratakashi.youtube.domain.magic.Map

data class Snippet(
    @field:SerializedName("publishedAt")
    @Map("publishedAt")
    val publishedAt: String,
    @field:SerializedName("channelId")
    @Map("channelId")
    val channelId: String,
    @field:SerializedName("title")
    @Map("title")
    val title: String,
    @field:SerializedName("description")
    @Map("description")
    val description: String,
    @field:SerializedName("channelTitle")
    @Map("channelTitle")
    val channelTitle: String,
    @field:SerializedName("categoryId")
    @Map("categoryId")
    val categoryId: String,
    @field:SerializedName("thumbnails")
    val thumbnails: Thumbnails
)
