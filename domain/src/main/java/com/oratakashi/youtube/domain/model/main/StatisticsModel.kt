package com.oratakashi.youtube.domain.model.main

import com.oratakashi.youtube.domain.magic.Map

data class StatisticsModel(
    @Map("viewCount")
    val viewCount: String = "",
    @Map("likeCount")
    val likeCount: String = "",
    @Map("dislikeCount")
    val dislikeCount: String = "",
    @Map("favoriteCount")
    val favoriteCount: String = "",
    @Map("commentCount")
    val commentCount: String = ""
)