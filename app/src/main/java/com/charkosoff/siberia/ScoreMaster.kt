package com.charkosoff.siberia

enum class ScoreChange(val check:Boolean, val plus:Int, val desc:String) // класс для действий игрока, которые приводят к изменению счёта
{
    ADD_RIGHT_FERTILIZE(true, 5, "Вы использовали удобрение"),
    ADD_RIGHT_CULTURE(true, 20, "Вы правильно наследовали культуру")
}

object ScoreChanges{
    val scoreChanges = mutableListOf<ScoreChange>() // лист с действиями игрока, которые приводят к изменению счёта
}