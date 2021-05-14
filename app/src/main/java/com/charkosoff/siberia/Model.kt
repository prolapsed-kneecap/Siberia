package com.charkosoff.siberia

import android.content.Context
import android.os.CountDownTimer
import android.widget.Toast

class Model():TimeInterface{
    private var time:Long = 0
    private val timeTable = mapOf<LongRange, String>(
        0..2500L to "МартⅠ",
        2500..5000L to "МартмъⅡ",
        5000..7500L to "АпрельⅠ",
        7500..10000L to "АпрельⅡ",
        10000..12500L to "МайⅠ",
        12500..15000L to "МайⅡ",
        15000..17500L to "ИюньⅠ",
        17500..20000L to "ИюньⅡ",
    )
    var countDownTimer = object:CountDownTimer(20000, 1000) {
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
        return timeTable.entries.find {time in it.key}?.value ?: throw Exception("Выход за календарные границы")
    }
}