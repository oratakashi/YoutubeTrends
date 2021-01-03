package com.oratakashi.youtube.data.model.main

import com.google.gson.annotations.SerializedName

data class Thumbnails(
    @field:SerializedName("medium") val medium: Medium?,
    @field:SerializedName("standard") val standard: Standard?
)
