package com.oratakashi.youtube.core.data.model.main

import com.google.gson.annotations.SerializedName

data class Items(
    @field:SerializedName("kind") val kind: String,
    @field:SerializedName("etag") val etag: String,
    @field:SerializedName("id") val id: String,
    @field:SerializedName("snippet") val snippet: Snippet,
    @field:SerializedName("statistics") val statistics: Statistics,
)
