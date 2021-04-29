package com.charkosoff.siberia.classes

import com.charkosoff.siberia.R

class Fertilizers(val name: String) {
    val description = when (name) {
        "Азотные" -> R.string.description_azotnie
        "Фосфорные" -> R.string.description_phosphornie
        "Калийные" -> R.string.description_kaliynie
        "Известковые" -> R.string.description_izvestkovie
        else -> R.string.description_hlorosoder
    }
    /*val image = when (name) {
        "Азотные" -> R.drawable.oves
        "Фосфорные" -> R.drawable.pshenitsa
        "Калийные" -> R.drawable.yachmen
        "Известковые" -> R.drawable.goroh
        "Хлорсодержащие" -> R.drawable.fasol
        else -> R.drawable.field
    } */
  /*  val family = when (name) {
        "Горох", "Фасоль" -> "Бобовые"
        else -> "Злаковые"
    } */
}

object FertilizersList {
    var fertilizers: MutableList<Fertilizers> = mutableListOf(
        Fertilizers("Азотные"),
        Fertilizers("Фосфорные"),
        Fertilizers("Калийные"),
        Fertilizers("Известковые"),
        Fertilizers("Хлорсодержащие")
    )
}

