package com.oratakashi.youtube.core.data.model.main

import com.google.gson.annotations.SerializedName

data class Medium(
    @field:SerializedName("url") val url: String,
    @field:SerializedName("width") val width: Int,
    @field:SerializedName("height") val height: Int
)
