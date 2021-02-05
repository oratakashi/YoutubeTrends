package com.oratakashi.youtube.data.model.main

import com.google.gson.annotations.SerializedName
import com.oratakashi.youtube.domain.magic.Map

data class Standard(
    @field:SerializedName("url")
    @Map("url")
    val url: String,
    @field:SerializedName("width")
    @Map("width")
    val width: Int,
    @field:SerializedName("height")
    @Map("height")
    val height: Int
)
