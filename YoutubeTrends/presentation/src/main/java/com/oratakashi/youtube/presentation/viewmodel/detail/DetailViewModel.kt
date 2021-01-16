package com.oratakashi.youtube.presentation.viewmodel.detail

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.youtube.domain.usecase.UseCase
import com.oratakashi.youtube.presentation.magic.toFavoriteModel
import com.oratakashi.youtube.presentation.model.main.Items

class DetailViewModel(
    private val useCase: UseCase
) : ViewModel() {
    private val state : MutableLiveData<Boolean> by lazy {
        MutableLiveData()
    }

    fun add(data : Items){
        useCase.add(data.toFavoriteModel())
    }

    fun getState(data: Items, lifecycleOwner: LifecycleOwner) : LiveData<Boolean>{
        useCase.getFavState().observe(lifecycleOwner, { state.postValue(it) })
        useCase.checkDataByID(data.toFavoriteModel())
        return state
    }
}