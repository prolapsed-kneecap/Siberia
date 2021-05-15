package com.charkosoff.siberia

import com.charkosoff.siberia.classes.Technics

interface EventInterface {
    fun getCurrentEvent(): String
    fun isTechChoiceRight(event:String, tech:Technics):Boolean
//    fun eventTimeTable(): Map<String, Event>
}