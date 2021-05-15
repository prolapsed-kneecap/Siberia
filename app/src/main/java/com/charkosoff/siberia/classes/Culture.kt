package com.charkosoff.siberia.classes

import com.charkosoff.siberia.R

class Culture(val name: String){
    val description = when (name) {
        "Овёс" -> R.string.description_oves
        "Пшеница" -> R.string.description_pshenitsya
        "Ячмень" -> R.string.description_yachmen
        "Горох" -> R.string.description_goroh
        else -> R.string.description_fasol
    }
    val image = when (name) {
        "Овёс" -> R.drawable.oves
        "Пшеница" -> R.drawable.pshenitsa
        "Ячмень" -> R.drawable.yachmen
        "Горох" -> R.drawable.goroh
        "Фасоль" -> R.drawable.fasol
        else -> R.drawable.field1
    }
    val family = when (name) {
        "Горох", "Фасоль" -> "Бобовые"
        else -> "Злаковые"
    }
}



object Plants {
    var cultures: MutableList<Culture> = mutableListOf(
        Culture("Овёс"),
        Culture("Пшеница"),
        Culture("Ячмень"),
        Culture("Горох"),
        Culture("Фасоль")
    )
}
