package com.charkosoff.siberia

import android.content.Context
import android.os.CountDownTimer
import android.widget.Toast

class Model():TimeInterface{
    private var time:Long = 0
    private val timeTable = mapOf<LongRange, String>(
        0..5000L to "Март",
        5000..10000L to "Апрель"
    )
    var countDownTimer = object:CountDownTimer(50000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            time = millisUntilFinished
        }
        override fun onFinish() {

        }
    }

    override fun getCurrent(): Long {
        return time
    }

    override fun timeTable(): Map<LongRange, String> {
        return timeTable
    }

    fun getMonth(time:Long, timeTable:Map<LongRange, String>):String{
        return timeTable.entries.find { time in it.key }?.value ?: throw Exception("Выход за календарные границы")
    }
}