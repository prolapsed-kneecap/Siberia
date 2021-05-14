package com.charkosoff.siberia.classes

class Fields(val id: Int) {
    var userSequenceCulture = mutableListOf<String>()

}

object ListOfFields {
    var fields: MutableList<Fields> = mutableListOf(
        Fields(0),
        Fields(1),
        Fields(2),
        Fields(3)
    )
}