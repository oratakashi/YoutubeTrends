package com.oratakashi.youtube.data.repository

import androidx.lifecycle.LiveData
import com.oratakashi.youtube.data.repository.local.LocalRepository
import com.oratakashi.youtube.data.repository.remote.RemoteRepository
import com.oratakashi.youtube.domain.repository.Repository
import com.oratakashi.youtube.domain.state.DomainMainState

class DataRepository (
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
}