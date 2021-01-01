package com.oratakashi.youtube.data.model.fav

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Snippet(
    @PrimaryKey
    val id : String,
    val publishedAt: String,
    val channelId: String,
    val title: String,
    val description: String,
    val channelTitle: String,
    val categoryId: String
)
