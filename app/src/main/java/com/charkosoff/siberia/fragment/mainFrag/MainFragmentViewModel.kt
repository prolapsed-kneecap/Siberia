package com.charkosoff.siberia.fragment.mainFrag

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.charkosoff.siberia.Repository
import com.charkosoff.siberia.resulted
import com.charkosoff.siberia.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragmentViewModel(
    private val repository: Repository
):ViewModel() {
    var times: MutableLiveData<Resource<Long>> = repository.timer
    fun loadTime() {
        viewModelScope.launch(Dispatchers.Default){
            repository.startTimer()
        }
    }
    fun cancelTimer(){
        repository.getTimer.cancel()
    }
}