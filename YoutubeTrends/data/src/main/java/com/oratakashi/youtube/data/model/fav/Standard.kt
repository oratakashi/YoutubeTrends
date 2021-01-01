package com.oratakashi.youtube.data.model.fav

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Standard(
    @PrimaryKey
    val id : String,
    val url: String,
    val width: Int,
    val height: Int
)
