package com.oratakashi.youtube.domain.repository

import androidx.lifecycle.LiveData
import com.oratakashi.youtube.domain.model.favorite.FavoriteModel
import com.oratakashi.youtube.domain.state.DomainMainState

interface Repository {
    fun getTrends() : LiveData<DomainMainState>
    fun getGames() : LiveData<DomainMainState>
    fun getMusic() : LiveData<DomainMainState>
    fun getSport() : LiveData<DomainMainState>

    fun add(data : FavoriteModel)
    fun delete(data : FavoriteModel)

    suspend fun getById(data : FavoriteModel) : List<FavoriteModel>
    suspend fun getAll() : List<FavoriteModel>
}