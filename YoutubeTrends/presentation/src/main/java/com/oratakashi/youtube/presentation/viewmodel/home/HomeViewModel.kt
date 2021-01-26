package com.oratakashi.youtube.presentation.viewmodel.home

import androidx.lifecycle.*
import com.oratakashi.youtube.domain.state.DomainMainState
import com.oratakashi.youtube.domain.usecase.UseCase
import com.oratakashi.youtube.presentation.magic.toItems
import com.oratakashi.youtube.presentation.model.main.Items
import com.oratakashi.youtube.presentation.state.MainState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val useCase: UseCase
) : ViewModel() {

    private val state: MutableLiveData<MainState> by lazy {
        MutableLiveData()
    }

    fun getTrends(lifecycleOwner: LifecycleOwner): LiveData<MainState> {
        useCase.getTrends().observe(lifecycleOwner, {
            when (it) {
                is DomainMainState.Loading -> state.postValue(MainState.Loading)
                is DomainMainState.Result -> CoroutineScope(Dispatchers.IO).launch {
                    val data: MutableList<Items> = ArrayList()
                    it.data.forEach { item ->
                        data.add(item.toItems())
                    }
                    withContext(Dispatchers.Main) {
                        state.postValue(MainState.Result(data))
                    }
                }
                is DomainMainState.Error -> state.postValue(MainState.Error(it.error))
            }
        })
        return state
    }
}