package com.oratakashi.youtube.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oratakashi.youtube.domain.model.favorite.FavoriteModel
import com.oratakashi.youtube.domain.state.DomainMainState

interface Repository {
    fun getTrends(): LiveData<DomainMainState>
    fun getGames(): LiveData<DomainMainState>
    fun getMusic(): LiveData<DomainMainState>
    fun getSport(): LiveData<DomainMainState>

    fun add(data: FavoriteModel, state: MutableLiveData<Boolean>)
    fun getById(data: FavoriteModel, state: MutableLiveData<Boolean>)
    fun getAll(state: MutableLiveData<List<FavoriteModel>>)
    fun getByCategory(id: String, state: MutableLiveData<List<FavoriteModel>>)
}