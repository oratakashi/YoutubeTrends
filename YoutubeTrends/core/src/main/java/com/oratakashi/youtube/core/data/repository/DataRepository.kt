package com.oratakashi.youtube.core.data.repository

import com.oratakashi.youtube.core.data.repository.local.LocalRepository
import com.oratakashi.youtube.core.data.repository.remote.RemoteRepository
import javax.inject.Inject

class DataRepository (
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : Repository {

}