package com.oratakashi.youtube.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oratakashi.youtube.domain.model.favorite.FavoriteModel
import com.oratakashi.youtube.domain.state.DomainMainState

interface UseCase {

    fun getTrends() : LiveData<DomainMainState>
    fun getGames() : LiveData<DomainMainState>
    fun getMusic() : LiveData<DomainMainState>
    fun getSport() : LiveData<DomainMainState>

    fun add(data : FavoriteModel)
    fun checkDataByID(data : FavoriteModel)
    fun getAll()
    fun getFavState() : LiveData<Boolean>
    fun getFavListState() : LiveData<List<FavoriteModel>>
}