package com.oratakashi.youtube.core.data.model.main

import com.google.gson.annotations.SerializedName

data class Statistics(
    @field:SerializedName("viewCount") val viewCount: String,
    @field:SerializedName("likeCount") val likeCount: String,
    @field:SerializedName("dislikeCount") val dislikeCount: String,
    @field:SerializedName("favoriteCount") val favoriteCount: String,
    @field:SerializedName("commentCount") val commentCount: String
)
