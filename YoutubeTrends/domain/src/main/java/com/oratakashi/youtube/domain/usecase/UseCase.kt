package com.oratakashi.youtube.domain.usecase

import androidx.lifecycle.LiveData
import com.oratakashi.youtube.domain.state.DomainMainState

interface UseCase {
    fun getTrends() : LiveData<DomainMainState>
    fun getGames() : LiveData<DomainMainState>
    fun getMusic() : LiveData<DomainMainState>
    fun getSport() : LiveData<DomainMainState>
}