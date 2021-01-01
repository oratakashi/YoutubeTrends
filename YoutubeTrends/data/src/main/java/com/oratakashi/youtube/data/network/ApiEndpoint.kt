package com.oratakashi.youtube.data.network

import com.oratakashi.youtube.data.model.main.ResponseMain
import io.reactivex.Single
import retrofit2.http.GET

interface ApiEndpoint {
    @GET("videos")
    fun getTrends(
        videoCategoryId : String = "",
        part : String = "snippet,statistics,id",
        chart : String = "mostPopular",
        regionCode : String = "ID"
    ): Single<ResponseMain>
}