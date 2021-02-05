package com.oratakashi.youtube.data.model.main

import com.google.gson.annotations.SerializedName
import com.oratakashi.youtube.domain.magic.Map

data class Medium(
    @field:SerializedName("url")
    @Map("url")
    val url: String,
    @field:SerializedName("width")
    @Map("width")
    val width: Int,
    @field:SerializedName("height")
    @Map("width")
    val height: Int
)
