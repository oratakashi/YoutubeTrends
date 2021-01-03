package com.oratakashi.youtube.data.repository.remote

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oratakashi.youtube.data.magic.toItemModels
import com.oratakashi.youtube.data.network.ApiEndpoint
import com.oratakashi.youtube.domain.repository.Repository
import com.oratakashi.youtube.domain.state.DomainMainState
import io.reactivex.disposables.CompositeDisposable

class RemoteRepository(
    private val endpoint: ApiEndpoint,
    private val context: Context
) : Repository {

    private val disposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun getTrends(): LiveData<DomainMainState> {
        val liveData: MutableLiveData<DomainMainState> = MutableLiveData()

        endpoint.getTrends()
            .map<DomainMainState> {
                DomainMainState.Result(it.toItemModels())
            }
            .onErrorReturn(DomainMainState::Error)
            .toFlowable()
            .startWith(DomainMainState.Loading)
            .subscribe(liveData::postValue)
            .let { return@let disposable::add }


        return liveData
    }

    override fun getGames(): LiveData<DomainMainState> {
        val liveData: MutableLiveData<DomainMainState> = MutableLiveData()

        endpoint.getTrends("20")
            .map<DomainMainState> {
                DomainMainState.Result(it.toItemModels())
            }
            .onErrorReturn(DomainMainState::Error)
            .toFlowable()
            .startWith(DomainMainState.Loading)
            .subscribe(liveData::postValue)
            .let { return@let disposable::add }

        return liveData
    }

    override fun getMusic(): LiveData<DomainMainState> {
        val liveData: MutableLiveData<DomainMainState> = MutableLiveData()

        endpoint.getTrends("10")
            .map<DomainMainState> {
                DomainMainState.Result(it.toItemModels())
            }
            .onErrorReturn(DomainMainState::Error)
            .toFlowable()
            .startWith(DomainMainState.Loading)
            .subscribe(liveData::postValue)
            .let { return@let disposable::add }

        return liveData
    }

    override fun getSport(): LiveData<DomainMainState> {
        val liveData: MutableLiveData<DomainMainState> = MutableLiveData()

        endpoint.getTrends("17")
            .map<DomainMainState> {
                DomainMainState.Result(it.toItemModels())
            }
            .onErrorReturn(DomainMainState::Error)
            .toFlowable()
            .startWith(DomainMainState.Loading)
            .subscribe(liveData::postValue)
            .let { return@let disposable::add }

        return liveData
    }
}