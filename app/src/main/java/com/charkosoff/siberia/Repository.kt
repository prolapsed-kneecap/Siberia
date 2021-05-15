package com.charkosoff.siberia

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import com.charkosoff.siberia.data.PlayButton
import com.charkosoff.siberia.utils.Resource
import java.lang.Exception

class Repository {

    val START_TIME = 0L
    var timer : MutableLiveData<Resource<Long>> = MutableLiveData(Resource.Loading(START_TIME))
    var globalTimer : MutableLiveData<Resource<Long>> = MutableLiveData(Resource.Loading(START_TIME))

    private var start : Long = 0L
    private var speed : Long = 1000L
    private var stop : Long = 20000L
    private var currentTime = 0L
    private val timeUpdater = 1000L

    private var globalStop:Long = 120000L
    private var globalCurrentTime = 0L

    fun startTimer(){
        var prevTime = System.currentTimeMillis()
        stop=prevTime+20000L
        var startTime=prevTime
        while (currentTime+startTime < stop){
            if(PlayButton.isSpeeded)
                speed=2000L
            else
                speed=1000L
            if (System.currentTimeMillis() - prevTime > timeUpdater)   {
                currentTime += speed
                if(currentTime>20000)
                    currentTime=20000
                timer.postValue(Resource.Loading(currentTime))
                prevTime = System.currentTimeMillis()
            }
        }
        timer.postValue(Resource.Success(currentTime))
    }
    fun startGlobalTimer(){
        var prevTime = System.currentTimeMillis()
        globalStop+=prevTime
        var startTime=prevTime
        while (globalCurrentTime+startTime < globalStop){
            if(PlayButton.isSpeeded)
                speed=2000L
            else
                speed=1000L
            if (System.currentTimeMillis() - prevTime > timeUpdater)   {
                globalCurrentTime += speed
                if(globalCurrentTime>120000)
                    globalCurrentTime=120000
                globalTimer.postValue(Resource.Loading(globalCurrentTime))
                prevTime = System.currentTimeMillis()
            }
        }
        globalTimer.postValue(Resource.Success(currentTime))
    }


    val getTimer : CountDownTimer by lazy {
        val a = object:CountDownTimer(START_TIME, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (timer.value?.data?.minus(1000L)!!>0L)
                    timer.postValue(Resource.Loading(timer.value?.data?.minus(1000L)))
                    /*if (PlayButton.isSpeeded)
                        timer.postValue(Resource.Loading(timer.value?.data?.minus(2000L)))
                    else
                        timer.postValue(Resource.Loading(timer.value?.data?.minus(1000L)))*/
                else
                    timer.postValue((Resource.Loading(0L)))
            }
            override fun onFinish() {
                timer.postValue(Resource.Success(0L))
            }
        }
        a.start()
    }
}
