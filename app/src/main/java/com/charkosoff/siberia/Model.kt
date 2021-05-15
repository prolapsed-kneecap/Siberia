package com.charkosoff.siberia

import com.charkosoff.siberia.Event.EventMaster
import com.charkosoff.siberia.classes.Technics
import com.charkosoff.siberia.data.Data

class Model :TimeInterface, EventInterface{
    private val eventMaster = EventMaster()
    private var time:Long = 0
    private var globalTime:Long = 0
    private val month = Data.currentMonth
    private val monthTimeTable = mapOf<LongRange, String>(
        0..5000L to "Март",
        5000..10000L to "Апрель",
        10000..15000L to "Май",
        15000..20000L to "Июнь",
        20000..25000L to "Июль",
        25000..30000L to "Август",
        //костыль)
        30000..35000L to "Март",
        35000..40000L to "Апрель",
        40000..45000L to "Май",
        45000..50000L to "Июнь",
        50000..55000L to "Июль",
        55000..60000L to "Август",
        //
        60000..65000L to "Март",
        65000..70000L to "Апрель",
        70000..75000L to "Май",
        75000..80000L to "Июнь",
        80000..85000L to "Июль",
        85000..90000L to "Август",
        //
        90000..95000L to "Март",
        95000..100000L to "Апрель",
        100000..105000L to "Май",
        105000..110000L to "Июнь",
        110000..115000L to "Июль",
        115000..120000L to "Август"
        )
    private val eventsTimeTable = mapOf(
        "Март" to "Посев",//Event("Посев"),
        "Апрель" to "Обработка",//Event("Обработка"),
        "Май" to "Обработка",//Event("Обработка"),
        "Июнь" to "Уборка",
        "Июль" to "Уборка",
        "Август" to "Уборка",
        "Сентябрь" to "Уборка"//Event("Уборка")
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

  /* override fun eventTimeTable(): Map<String, Event> {
        return eventsTimeTable
    } */

    override fun isTechChoiceRight(event:String, tech:Technics): Boolean {
        return eventMaster.isTechChoiceRight(event, tech)
    }
}