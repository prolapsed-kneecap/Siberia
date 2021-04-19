package com.charkosoff.siberia

import com.charkosoff.siberia.Classes.Culture
import com.charkosoff.siberia.Classes.Plants.cultures

class MasterCulture(){

    private val map_Positive_culture= mutableMapOf<Culture,List<Culture>>(
        cultures[0] to listOf(cultures[2],cultures[4]),
        cultures[1] to listOf( cultures[1]),
        cultures[2] to listOf(cultures[4],cultures[3]),
    )


    fun howIsGoodChoice(prev : String, next : String) : Int{
        val isGoodNext = map_Positive_culture[prev]?.contains(next) ?: false
        return if (isGoodNext)
            250
        else
            10
    }




}