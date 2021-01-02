package com.oratakashi.youtube.domain.interactor

import androidx.lifecycle.LiveData
import com.oratakashi.youtube.domain.repository.Repository
import com.oratakashi.youtube.domain.state.DomainMainState
import com.oratakashi.youtube.domain.usecase.UseCase

class Interactor constructor(
    private val repository: Repository
) : UseCase {
    override fun getTrends(): LiveData<DomainMainState> = repository.getTrends()
}