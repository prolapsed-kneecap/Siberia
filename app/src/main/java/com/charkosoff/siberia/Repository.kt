package com.charkosoff.siberia

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import com.charkosoff.siberia.utils.Resource

class Repository {

    val START_TIME = 20000L
    var timer : MutableLiveData<Resource<Long>> = MutableLiveData(Resource.Loading(START_TIME))

    fun loadTime() {
        val countDownTimer = object : CountDownTimer(START_TIME, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timer.value = Resource.Loading(timer.value?.data?.minus(1000L))
            }

            override fun onFinish() {
                timer.value = Resource.Success(0L)
            }
        }
        countDownTimer.start()
    }
}
