package com.charkosoff.siberia.Classes

class Culture(val name:String) {
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

