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
    val image = when (name) {
        "СЗП-3,6" -> R.drawable.szp
        "СЗУ-Т-3.6" ->R.drawable.szytt
        "СЗУ-3,6-04" -> R.drawable.szy
        "Енисей–1200–1НМ" -> R.drawable.inesei
        else -> R.drawable.don
    }
    val family = when(name) {
        "Енисей–1200–1НМ", "Дон 1500" -> "Уборка"
       "СЗП-3,6" -> "Обработка"
        "СЗУ-Т-3.6", "СЗУ-3,6-04" -> "Посев"
       else -> "Нет"
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