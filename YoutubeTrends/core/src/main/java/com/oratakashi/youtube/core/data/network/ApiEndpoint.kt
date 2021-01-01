package com.oratakashi.youtube.core.data.network

import com.oratakashi.youtube.core.data.model.main.ResponseMain
import io.reactivex.Single
import retrofit2.http.GET

interface ApiEndpoint {
    @GET("videos")
    fun getTrends(): Single<ResponseMain>
}