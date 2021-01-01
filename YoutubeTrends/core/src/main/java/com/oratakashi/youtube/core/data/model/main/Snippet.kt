package com.oratakashi.youtube.core.data.model.main

import com.google.gson.annotations.SerializedName

data class Snippet(
    @field:SerializedName("publishedAt") val publishedAt: String,
    @field:SerializedName("channelId") val channelId: String,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("description") val description: String,
    @field:SerializedName("channelTitle") val channelTitle: String,
    @field:SerializedName("categoryId") val categoryId: String,
    @field:SerializedName("thumbnails") val thumbnails: Thumbnails
)
