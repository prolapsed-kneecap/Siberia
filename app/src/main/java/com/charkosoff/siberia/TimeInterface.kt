package com.charkosoff.siberia

interface TimeInterface {
    fun getCurrent():Long
    fun timeTable():Map<LongRange, String>
}