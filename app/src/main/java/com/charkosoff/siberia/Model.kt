package com.charkosoff.siberia

import android.content.Context
import android.os.CountDownTimer
import android.widget.Toast

class Model(context:Context):TimeInterface {
    private var time:Long = 0
    private val timeTable = mapOf<String, IntRange>(
        "Посев" to 5000..15000,
        "Сбор" to 30000..45000
    )
    var countDownTimer = object:CountDownTimer(50000, 0) {
        override fun onTick(millisUntilFinished: Long) {
            time = millisUntilFinished
        }

        override fun onFinish() {
            Toast.makeText(context, "Timer is over!", Toast.LENGTH_SHORT).show()
        }

    }

    override fun getCurrent(): Long {
        return time
    }

    override fun timeTable(): Map<String, IntRange> {
        return timeTable
    }
}