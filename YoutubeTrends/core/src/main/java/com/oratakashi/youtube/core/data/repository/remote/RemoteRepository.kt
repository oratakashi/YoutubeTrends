package com.oratakashi.youtube.core.data.repository.remote

import com.oratakashi.youtube.core.data.network.ApiEndpoint
import com.oratakashi.youtube.core.data.repository.Repository
import javax.inject.Inject

class RemoteRepository (
    private val endpoint: ApiEndpoint
) : Repository {

}