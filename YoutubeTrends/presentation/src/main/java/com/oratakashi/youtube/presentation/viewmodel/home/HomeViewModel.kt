package com.oratakashi.youtube.presentation.viewmodel.home

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.youtube.domain.state.DomainMainState
import com.oratakashi.youtube.domain.usecase.UseCase
import com.oratakashi.youtube.presentation.state.MainState

class HomeViewModel(
    private val useCase: UseCase
) : ViewModel() {

    private val state : MutableLiveData<MainState> by lazy {
        MutableLiveData()
    }

    fun getTrends(lifecycleOwner: LifecycleOwner) : LiveData<MainState>{
        useCase.getTrends().observe(lifecycleOwner, {
            when(it){
                is DomainMainState.Loading  -> state.postValue(MainState.Loading)
                is DomainMainState.Result   -> {

                }
                is DomainMainState.Error    -> state.postValue(MainState.Error(it.error))
            }
        })
        return state
    }
}