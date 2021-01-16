package com.oratakashi.youtube.domain.model.favorite

import com.oratakashi.youtube.domain.magic.Map

data class FavoriteModel(
    @Map("id")
    val id: String = "",
    @Map("publishedAt")
    val publishedAt: String = "",
    @Map("channelId")
    val channelId: String = "",
    @Map("title")
    val title: String = "",
    @Map("description")
    val description: String = "",
    @Map("channelTitle")
    val channelTitle: String = "",
    @Map("categoryId")
    val categoryId: String = "",
    @Map("urlMedium")
    val urlMedium: String = "",
    @Map("urlStandard")
    val urlStandard: String = "",
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
