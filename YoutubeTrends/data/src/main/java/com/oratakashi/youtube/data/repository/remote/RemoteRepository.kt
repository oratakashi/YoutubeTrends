package com.oratakashi.youtube.data.repository.remote

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oratakashi.youtube.data.network.ApiEndpoint
import com.oratakashi.youtube.domain.repository.Repository
import com.oratakashi.youtube.domain.state.MainState
import io.reactivex.disposables.CompositeDisposable

class RemoteRepository (
    private val endpoint: ApiEndpoint,
    private val context : Context
) : Repository {

    private val disposable : CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun getTrends(): LiveData<MainState> {
        val liveData : MutableLiveData<MainState> = MutableLiveData()



        return liveData
    }
}