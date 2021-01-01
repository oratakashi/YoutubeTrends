package com.oratakashi.youtube.data.model.fav

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Items(
    val kind: String,
    val etag: String,
    @PrimaryKey val id: String
)
