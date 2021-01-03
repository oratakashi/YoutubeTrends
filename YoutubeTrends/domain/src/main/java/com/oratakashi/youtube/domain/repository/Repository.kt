package com.oratakashi.youtube.domain.repository

import androidx.lifecycle.LiveData
import com.oratakashi.youtube.domain.state.DomainMainState

interface Repository {
    fun getTrends() : LiveData<DomainMainState>
    fun getGames() : LiveData<DomainMainState>
    fun getMusic() : LiveData<DomainMainState>
    fun getSport() : LiveData<DomainMainState>
}