package com.charkosoff.siberia.classes

import com.charkosoff.siberia.R

class Technics(val name: String) {
    val description = when (name) {
        "СЗП-3,6" -> R.string.description_szp_3_6
        "СЗУ-Т-3.6" -> R.string.description_szu_t
        "СЗУ-3,6-04" -> R.string.description_szu_z
        "Енисей–1200–1НМ" -> R.string.description_enisey
        else -> R.string.description_don
    }
   /* val image = when (name) {
        "СЗП-3,6" -> R.string.description_szp_3_6
        "СЗУ-Т-3.6" -> R.string.description_szu_t
        "СЗУ-3,6-04" -> R.string.description_szu_3
        "Енисей–1200–1НМ" -> R.string.description_enisey
        else -> R.string.description_don
    } */
    val family = when (name) {
        "Енисей–1200–1НМ", "Дон 1500" -> "Комбайн"
        else -> "Сеялка"
    }
}

object TechnicsList {
    var technics: MutableList<Technics> = mutableListOf(
        Technics("СЗП-3,6"),
        Technics("СЗУ-Т-3.6"),
        Technics("СЗУ-3,6-04"),
        Technics("Енисей–1200–1НМ"),
        Technics("Дон 1500")
    )
}