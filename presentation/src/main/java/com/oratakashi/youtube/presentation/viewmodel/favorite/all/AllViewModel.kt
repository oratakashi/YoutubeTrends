package com.oratakashi.youtube.presentation.viewmodel.favorite.all

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.youtube.domain.usecase.UseCase
import com.oratakashi.youtube.presentation.magic.toItems
import com.oratakashi.youtube.presentation.model.main.Items

class AllViewModel(
    private val useCase: UseCase
) : ViewModel() {
    private val state: MutableLiveData<List<Items>> by lazy {
        MutableLiveData()
    }

    fun getAll(lifecycleOwner: LifecycleOwner): LiveData<List<Items>> {
        useCase.getFavListState().observe(lifecycleOwner, { state.postValue(it.toItems()) })
        useCase.getAll()
        return state
    }

    fun getAll() {
        useCase.getAll()
    }
}