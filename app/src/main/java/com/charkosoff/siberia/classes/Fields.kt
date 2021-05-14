package com.charkosoff.siberia.classes

class Fields(val id: Int) {
    var userSequenceCulture = mutableListOf<String>()

    var score = 1

    fun percentOfAnswer(): Double {
        return (score * 100.0) / userSequenceCulture.size
    }

}

object ListOfFields {
    var fields: MutableList<Fields> = mutableListOf(
        Fields(0),
        Fields(1),
        Fields(2),
        Fields(3)
    )
}