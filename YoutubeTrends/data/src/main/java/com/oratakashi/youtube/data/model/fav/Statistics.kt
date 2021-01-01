package com.oratakashi.youtube.data.model.fav

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Statistics(
    @PrimaryKey
    val id : String,
    val viewCount: String,
    val likeCount: String,
    val dislikeCount: String,
    val favoriteCount: String,
    val commentCount: String
)
