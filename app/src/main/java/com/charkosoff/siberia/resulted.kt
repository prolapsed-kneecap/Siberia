package com.charkosoff.siberia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class resulted : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resulted)

        var score = intent.extras?.get("score")
        var res = findViewById<TextView>(R.id.resultTextView)
        res.text = score.toString()

    }
}