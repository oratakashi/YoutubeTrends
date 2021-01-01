package com.oratakashi.youtube.core.domain.interactor

import com.oratakashi.youtube.core.data.repository.Repository
import com.oratakashi.youtube.core.domain.usecase.UseCase
import javax.inject.Inject

class Interactor constructor(
    repository: Repository
) : UseCase {
    override fun getDummy(): String {
        return "Hello World"
    }
}