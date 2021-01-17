package com.oratakashi.youtube.presentation.viewmodel.favorite.all

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.youtube.domain.usecase.UseCase
import com.oratakashi.youtube.presentation.model.main.Items

class AllViewModel(
    private val useCase: UseCase
) : ViewModel() {
    private val state : MutableLiveData<List<Items>> by lazy {
        MutableLiveData()
    }

    fun getAll(lifecycleOwner: LifecycleOwner){
        useCase.getFavListState().observe(lifecycleOwner, {
            
        })
    }
}