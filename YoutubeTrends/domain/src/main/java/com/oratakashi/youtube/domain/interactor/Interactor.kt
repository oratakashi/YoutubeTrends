package com.oratakashi.youtube.domain.interactor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oratakashi.youtube.domain.model.favorite.FavoriteModel
import com.oratakashi.youtube.domain.repository.Repository
import com.oratakashi.youtube.domain.state.DomainMainState
import com.oratakashi.youtube.domain.usecase.UseCase

class Interactor constructor(
    private val repository: Repository
) : UseCase {
    private val favState: MutableLiveData<Boolean> = MutableLiveData()
    private val favListState: MutableLiveData<List<FavoriteModel>> = MutableLiveData()

    override fun getTrends(): LiveData<DomainMainState> = repository.getTrends()
    override fun getGames(): LiveData<DomainMainState> = repository.getGames()
    override fun getMusic(): LiveData<DomainMainState> = repository.getMusic()
    override fun getSport(): LiveData<DomainMainState> = repository.getSport()

    override fun add(data: FavoriteModel) = repository.add(data, favState)
    override fun checkDataByID(data: FavoriteModel) = repository.getById(data, favState)

    override fun getAll() = repository.getAll(favListState)

    override fun getByCategory(id: String) = repository.getByCategory(id, favListState)

    override fun getFavListState(): LiveData<List<FavoriteModel>> {
        return favListState
    }

    override fun getFavState(): LiveData<Boolean> {
        return favState
    }
}