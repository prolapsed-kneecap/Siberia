package com.charkosoff.siberia.fragment.allPlaces

import androidx.lifecycle.ViewModel
import com.charkosoff.siberia.Repository

class AllPlacesViewModel(
    private val repository: Repository
) : ViewModel() {
    fun loadData(){
        repository.load()
    }
}