package com.charkosoff.siberia.classes

import com.charkosoff.siberia.R

class Fertilizer(val name: String) {
    val description = when (name) {
        "Азотные" -> R.string.description_azotnia
        "Фосфорные" -> R.string.description_phosphor
        "Калийные" -> R.string.description_kaliinie
        "Известковые" -> R.string.description_izvest
        else -> R.string.description_chloro
    }
     val image = when (name) {
         "Азотные" -> R.drawable.azotnie
         "Фосфорные" -> R.drawable.phosphor
         "Калийные" -> R.drawable.kaliy_hlor
         "Известковые" -> R.drawable.izvest
         else -> R.drawable.hlor
     }
    val family = when (name) {
        ",,", ".." -> ".."
        else -> ".."
    }
}

object FertilizerList {
    var Fertilizers: MutableList<Fertilizer> = mutableListOf(
                Fertilizer("Азотные"),
                Fertilizer("Фосфорные"),
                Fertilizer("Калийные"),
                Fertilizer("Известковые"),
                Fertilizer("Хлорсодержащие"),
    )
}
