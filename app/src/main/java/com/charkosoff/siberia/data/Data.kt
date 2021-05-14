package com.charkosoff.siberia.data

import com.charkosoff.siberia.classes.Technics


object Data {
    var currentTech = Technics("Не указано")
    var currentEvent = "***"
    var currentTime = 0L
    var currentMonth = "Март"
    var currentId : Int = -1
    var currentCulture : MutableList<String> = mutableListOf("Паровое поле","Паровое поле","Паровое поле","Паровое поле")
    val culturesToShow = arrayOf("Овёс", "Пшеница", "Ячмень", "Горох", "Фасоль")
}
object PlayButton {
    var isSpeeded=false
}


