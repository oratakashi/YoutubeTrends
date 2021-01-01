package com.oratakashi.youtube.data.model.main

import com.google.gson.annotations.SerializedName
import com.oratakashi.youtube.domain.magic.Map

data class Items(
    @field:SerializedName("kind") val kind: String,
    @field:SerializedName("etag") val etag: String,
    @field:SerializedName("id")
    @Map("id")
    val id: String,
    @field:SerializedName("snippet") val snippet: Snippet,
    @field:SerializedName("statistics") val statistics: Statistics,
)
