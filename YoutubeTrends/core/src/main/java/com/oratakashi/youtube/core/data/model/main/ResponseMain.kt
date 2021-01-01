package com.oratakashi.youtube.core.data.model.main

import com.google.gson.annotations.SerializedName

data class ResponseMain(
    @field:SerializedName("kind") val kind: String,
    @field:SerializedName("etag") val etag: String,
    @field:SerializedName("nextPageToken") val nextPageToken: String,
    @field:SerializedName("items") val items: List<Items>,
)