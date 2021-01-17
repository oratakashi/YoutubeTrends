package com.oratakashi.youtube.domain.interactor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oratakashi.youtube.domain.model.favorite.FavoriteModel
import com.oratakashi.youtube.domain.repository.Repository
import com.oratakashi.youtube.domain.state.DomainMainState
import com.oratakashi.youtube.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Interactor constructor(
    private val repository: Repository
) : UseCase {
    private val favState: MutableLiveData<Boolean> = MutableLiveData()
    private val favListState: MutableLiveData<List<FavoriteModel>> = MutableLiveData()

    override fun getTrends(): LiveData<DomainMainState> = repository.getTrends()
    override fun getGames(): LiveData<DomainMainState> = repository.getGames()
    override fun getMusic(): LiveData<DomainMainState> = repository.getMusic()
    override fun getSport(): LiveData<DomainMainState> = repository.getSport()

    override fun add(data: FavoriteModel) {
        CoroutineScope(Dispatchers.IO).launch {
            val checkData = withContext(Dispatchers.IO) {
                repository.getById(data)
            }
            if (checkData.isEmpty()) {
                repository.add(data)
                withContext(Dispatchers.Main) {
                    favState.postValue(true)
                }
            } else {
                repository.delete(data)
                withContext(Dispatchers.Main) {
                    favState.postValue(false)
                }
            }
        }
    }

    override fun checkDataByID(data: FavoriteModel) {
        CoroutineScope(Dispatchers.IO).launch {
            val checkData = withContext(Dispatchers.IO) {
                repository.getById(data)
            }
            if (checkData.isNotEmpty()) {
                withContext(Dispatchers.Main) {
                    favState.postValue(true)
                }
            } else {
                withContext(Dispatchers.Main) {
                    favState.postValue(false)
                }
            }
        }
    }

    override fun getAll() {
        CoroutineScope(Dispatchers.IO).launch {
            val getData = withContext(Dispatchers.IO) {
                repository.getAll()
            }
            withContext(Dispatchers.Main) {
                favListState.postValue(getData)
            }
        }
    }

    override fun getFavListState(): LiveData<List<FavoriteModel>> {
        return favListState
    }

    override fun getFavState(): LiveData<Boolean> {
        return favState
    }
}