package com.charkosoff.siberia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.charkosoff.siberia.classes.Fields
import com.charkosoff.siberia.classes.ListOfFields
import com.charkosoff.siberia.data.Data

class ResultedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resulted)

        val res = findViewById<TextView>(R.id.resultTextView)
        res.text = ""
        var summ = 0.0
        ListOfFields.fields.forEach {
            summ+=it.percentOfAnswer()
        }
        res.text = ((summ/4.0).toInt().toString() + "% правльных саженцев")
    }
}