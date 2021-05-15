package com.charkosoff.siberia

import com.charkosoff.siberia.Event.EventMaster
import com.charkosoff.siberia.classes.Technics
import com.charkosoff.siberia.data.Data

class Model():TimeInterface, EventInterface{
    private val eventMaster = EventMaster()
    private var time:Long = 0
    private var globalTime:Long = 0
    private val month = Data.currentMonth
    private val monthTimeTable = mapOf<LongRange, String>(
        0..30000L to "Март",
        30000..60000L to "Апрель",
        60000..90000L to "Май",
        90000..120000L to "Июнь"
    )
    private val eventsTimeTable = mapOf(
        "Март" to "Посев",//Event("Посев"),
        "Апрель" to "Обработка",//Event("Обработка"),
        "Май" to "Обработка",//Event("Обработка"),
        "Июнь" to "Уборка"//Event("Уборка")
    )

    override fun getCurrent(): Long {
        return time
    }

    override fun getGlobalCurrent(): Long {
        return globalTime
    }

    override fun timeTable(): Map<LongRange, String> {
        return monthTimeTable
    }

    fun getMonth(time:Long, timeTable:Map<LongRange, String>):String{
        return timeTable.entries.find {time in it.key}?.value ?: throw Exception("Выход за календарные границы")
    }

    override fun getCurrentEvent(): String {
        return eventsTimeTable[Data.currentMonth]!!
    }

/*    override fun eventTimeTable(): Map<String, Event> {
        return eventsTimeTable
    }*/

    override fun isTechChoiceRight(event:String, tech:Technics): Boolean {
        return eventMaster.isTechChoiceRight(event, tech)
    }
}