package com.charkosoff.siberia

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import com.charkosoff.siberia.data.PlayButton
import com.charkosoff.siberia.utils.Resource
import java.lang.Exception

class Repository {

    val START_TIME = 20000L
    var timer : MutableLiveData<Resource<Long>> = MutableLiveData(Resource.Loading(START_TIME))

    private var start : Long = 0L
    private var speed : Long = 1000L
    private var stop : Long = 20000L
    private var currentTime = 0L
    private val timeUpdater = 1000L

    suspend fun startTimer(){
        var prevTime = System.currentTimeMillis()
        stop=prevTime+20000L
        currentTime=prevTime
        while (currentTime < stop){
            if (System.currentTimeMillis() - prevTime > timeUpdater)   {
                currentTime += speed
                timer.value = Resource.Loading(currentTime)
                prevTime = System.currentTimeMillis()
            }
        }
    }


    val getTimer : CountDownTimer by lazy {
        val a = object:CountDownTimer(START_TIME, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (timer.value?.data?.minus(1000L)!!>0L)
                    if (PlayButton.isSpeeded)
                        timer.value = (Resource.Loading(timer.value?.data?.minus(2000L)))
                    else
                        timer.value = (Resource.Loading(timer.value?.data?.minus(1000L)))
                else
                    timer.value = (Resource.Loading(0L))
            }
            override fun onFinish() {
                timer.postValue(Resource.Success(0L))
            }
        }
        a.start()
    }
}
