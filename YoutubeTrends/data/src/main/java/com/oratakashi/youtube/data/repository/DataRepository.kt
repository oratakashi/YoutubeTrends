package com.oratakashi.youtube.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oratakashi.youtube.data.repository.local.LocalRepository
import com.oratakashi.youtube.data.repository.remote.RemoteRepository
import com.oratakashi.youtube.domain.model.favorite.FavoriteModel
import com.oratakashi.youtube.domain.repository.Repository
import com.oratakashi.youtube.domain.state.DomainMainState

class DataRepository(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : Repository {
    override fun getTrends(): LiveData<DomainMainState> {
        return remoteRepository.getTrends()
    }

    override fun getGames(): LiveData<DomainMainState> {
        return remoteRepository.getGames()
    }

    override fun getMusic(): LiveData<DomainMainState> {
        return remoteRepository.getMusic()
    }

    override fun getSport(): LiveData<DomainMainState> {
        return remoteRepository.getSport()
    }

    override fun add(data: FavoriteModel, state: MutableLiveData<Boolean>) {
        return localRepository.add(data, state)
    }

    override fun getById(data: FavoriteModel, state: MutableLiveData<Boolean>) {
        localRepository.getById(data, state)
    }

    override fun getAll(state: MutableLiveData<List<FavoriteModel>>) {
        localRepository.getAll(state)
    }

    override fun getByCategory(id: String, state: MutableLiveData<List<FavoriteModel>>) {
        localRepository.getByCategory(id, state)
    }
}