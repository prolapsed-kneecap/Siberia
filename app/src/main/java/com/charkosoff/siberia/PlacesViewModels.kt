package com.charkosoff.siberia

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FirstPlaceViewModel:ViewModel(){
    var eventer:MutableLiveData<String> = MutableLiveData()
}
class SecondPlaceViewModel:ViewModel(){
    var eventer:MutableLiveData<String> = MutableLiveData()
}
class ThirdPlaceViewModel:ViewModel(){
    var eventer:MutableLiveData<String> = MutableLiveData()
}
class FourthPlaceViewModel:ViewModel(){
    var eventer:MutableLiveData<String> = MutableLiveData()
}