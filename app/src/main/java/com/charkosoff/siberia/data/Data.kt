package com.charkosoff.siberia.data

import com.charkosoff.siberia.classes.Technics


object Data {
    var currentTech = Technics("Не указано")
    var currentEvent = "Не указано"
    var currentTime = 0L
    var currentMonth = "Март"
    var currentId : Int = -1
    var currentCulture : MutableList<String> = mutableListOf("Паровое поле","Паровое поле","Паровое поле","Паровое поле")
    val culturesToShow = arrayListOf("Овёс", "Пшеница", "Ячмень", "Горох", "Фасоль")
    var globalTimerIsRunning = false
    var globalTimerIsStopped = false
    var globalTimerWasStarted = false
    var currentCheckedToogle = 0
}
object PlayButton {
    var isSpeeded=false
}


