package com.charkosoff.siberia

interface TimeInterface {
    fun getCurrent():Long
    fun getGlobalCurrent():Long
    fun timeTable():Map<LongRange, String>
}