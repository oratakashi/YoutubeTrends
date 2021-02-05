package com.oratakashi.youtube.presentation.model.main

import android.os.Parcelable
import com.oratakashi.youtube.domain.magic.Map
import kotlinx.parcelize.Parcelize

@Parcelize
data class Items(
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
) : Parcelable
