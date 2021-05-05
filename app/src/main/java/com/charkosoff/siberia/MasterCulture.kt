package com.charkosoff.siberia

import com.charkosoff.siberia.classes.Culture
import com.charkosoff.siberia.classes.Plants.cultures

class CultureMaster(){
    private val map_Positive_culture= mutableMapOf<Culture,List<Culture>>(
        cultures[0] to listOf(cultures[2],cultures[4]),
        cultures[1] to listOf(cultures[1]),
        cultures[2] to listOf(cultures[4],cultures[3]),
    )


    fun howIsGoodChoice(prev : Culture, next : Culture) : Int{
        val isGoodNext = map_Positive_culture[prev]?.contains(next)
        return if (isGoodNext!!)
            250
        else
            10
    }
}