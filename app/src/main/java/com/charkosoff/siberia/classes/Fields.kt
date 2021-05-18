package com.charkosoff.siberia.classes

class Fields(val id: Int) {
    var userSequenceCulture = mutableListOf<String>()

    var score = 0

   /* fun percentOfAnswer(): Double {
        return if (userSequenceCulture.size > 1)
            (score * 100.0) / (userSequenceCulture.size - 1)
        else
            0.0
    }*/

}

object ListOfFields {
    var fields: MutableList<Fields> = mutableListOf(
        Fields(0),
        Fields(1),
        Fields(2),
        Fields(3),
    )
}