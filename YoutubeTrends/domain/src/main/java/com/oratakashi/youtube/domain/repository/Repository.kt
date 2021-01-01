package com.oratakashi.youtube.domain.repository

import androidx.lifecycle.LiveData
import com.oratakashi.youtube.domain.state.MainState

interface Repository {
    fun getTrends() : LiveData<MainState>
}