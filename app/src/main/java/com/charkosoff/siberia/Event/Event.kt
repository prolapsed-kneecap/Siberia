package com.charkosoff.siberia.Event

import com.charkosoff.siberia.classes.Technics
import com.charkosoff.siberia.classes.TechnicsList

//class Event(name: String)
class EventMaster() {
    val eventToTechnic = mapOf<String, List<String>>(
        "Посев" to listOf(TechnicsList.technics[0].family, TechnicsList.technics[1].family),
        "Обработка" to listOf(TechnicsList.technics[2].family),
        "Уборка" to listOf(TechnicsList.technics[3].family)
    )
    fun isTechChoiceRight(event:String, tech:Technics):Boolean{
        return eventToTechnic[event]!!.contains(tech.family)
    }
}