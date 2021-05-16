package com.charkosoff.siberia.classes

import com.charkosoff.siberia.R

class Achievement(name:String, task:String, image:Int){
    val image = image
    val achivName = name
    val achivTask = task
    var isCompleted=false
}
object Achievements{
    val achievements = mutableListOf<Achievement>(
        Achievement("Захватчик", "Посадите 1000 культур.", R.drawable.akar_icons_plant),
        Achievement("Растениевод", "Соберите 10 пачек каждой культуры.", R.drawable.tabler_plant),
        Achievement("Куй железо, пока...", "Пройдите 1 уровень на 100 баллов.", R.drawable.whh_ironman)
    )
}