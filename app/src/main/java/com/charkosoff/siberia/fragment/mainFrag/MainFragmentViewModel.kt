package com.charkosoff.siberia.fragment.mainFrag

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.charkosoff.siberia.Repository
import com.charkosoff.siberia.utils.Resource

class MainFragmentViewModel(
    private val repository: Repository
):ViewModel() {
    var times: MutableLiveData<Resource<Long>> = repository.timer
    fun loadTime() {
        repository.loadTime()
    }
}