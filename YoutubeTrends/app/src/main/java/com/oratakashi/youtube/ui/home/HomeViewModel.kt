package com.oratakashi.youtube.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.youtube.core.domain.usecase.UseCase
import javax.inject.Inject

class HomeViewModel(
    private val useCase: UseCase
) : ViewModel() {
    val testLiveData : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun test(){
        testLiveData.postValue(useCase.getDummy())
    }
}