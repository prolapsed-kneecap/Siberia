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
    /* val image = when (name) {
         "СЗП-3,6" -> R.string.description_szp_3_6
         "СЗУ-Т-3.6" -> R.string.description_szu_t
         "СЗУ-3,6-04" -> R.string.description_szu_3
         "Енисей–1200–1НМ" -> R.string.description_enisey
         else -> R.string.description_don
     } */
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
