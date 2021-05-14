package com.charkosoff.siberia

import com.charkosoff.siberia.classes.Culture
import com.charkosoff.siberia.classes.Plants.cultures

class CultureMaster(){
    private val mapPositiveCulture= mutableMapOf<String,List<String>>(
        cultures[0].name to listOf(cultures[1].name,cultures[4].name),
        cultures[1].name to listOf(cultures[2].name),
        cultures[2].name to listOf(cultures[4].name,cultures[3].name),
        cultures[3].name to listOf(cultures[1].name),
        cultures[4].name to listOf(cultures[2].name)
    )


    fun howIsGoodChoice(prev : String, next : String) : Boolean {
        val isGoodNext = mapPositiveCulture[prev]?.contains(next)
        return isGoodNext!!
    }
}