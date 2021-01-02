package com.oratakashi.youtube.domain.repository

import androidx.lifecycle.LiveData
import com.oratakashi.youtube.domain.state.DomainMainState

interface Repository {
    fun getTrends() : LiveData<DomainMainState>
}