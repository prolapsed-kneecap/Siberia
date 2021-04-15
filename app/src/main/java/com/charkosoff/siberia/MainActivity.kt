package com.charkosoff.siberia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import androidx.core.widget.doOnTextChanged

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nameEditText: EditText = findViewById(R.id.editTextName)
        val groupEditText: EditText = findViewById(R.id.editTextGroup)
        val inButton:Button = findViewById(R.id.button)

        var user = User("Имя", "Группа")
        nameEditText.doOnTextChanged { text, start, before, count ->
            user.name = text.toString()
        }
        groupEditText.doOnTextChanged { text, start, before, count ->
            user.group = text.toString()
        }
        inButton.setOnClickListener {
            Toast.makeText(this, user.name+" "+user.group, LENGTH_LONG).show()
        }
    }
}