package com.oratakashi.youtube.data.model.main

import com.google.gson.annotations.SerializedName
import com.oratakashi.youtube.domain.magic.Map

data class Statistics(
    @field:SerializedName("viewCount")
    @Map("viewCount")
    val viewCount: String,
    @field:SerializedName("likeCount")
    @Map("likeCount")
    val likeCount: String,
    @field:SerializedName("dislikeCount")
    @Map("dislikeCount")
    val dislikeCount: String,
    @field:SerializedName("favoriteCount")
    @Map("favoriteCount")
    val favoriteCount: String,
    @field:SerializedName("commentCount")
    @Map("commentCount")
    val commentCount: String
)
