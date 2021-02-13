package com.oratakashi.youtube.data.network

import com.oratakashi.youtube.data.model.main.ResponseMain
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpoint {
    @GET("videos")
    fun getTrendsCategory(
        @Query("videoCategoryId") videoCategoryId: String,
        @Query("part") part: String = "snippet,statistics,id",
        @Query("chart") chart: String = "mostPopular",
        @Query("regionCode") regionCode: String = "ID",
        @Query("maxResults") maxResults: Int = 50
    ): Single<ResponseMain>

    @GET("videos")
    fun getTrends(
        @Query("part") part: String = "snippet,statistics,id",
        @Query("chart") chart: String = "mostPopular",
        @Query("regionCode") regionCode: String = "ID",
        @Query("maxResults") maxResults: Int = 50
    ): Single<ResponseMain>
}