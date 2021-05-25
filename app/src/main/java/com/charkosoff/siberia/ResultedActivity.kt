package com.charkosoff.siberia

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charkosoff.siberia.adapters.ScoreChangeRecyclerViewAdapter
import com.charkosoff.siberia.classes.Fields
import com.charkosoff.siberia.classes.ListOfFields
import com.charkosoff.siberia.data.Data

class ResultedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resulted)

        val res = findViewById<TextView>(R.id.resultTextView)
        res.text = ""

        var summ = 0

        Data.currentCulture = mutableListOf("Паровое поле","Паровое поле","Паровое поле","Паровое поле")

        val star1 = findViewById<ImageView>(R.id.star1)
        val star2 = findViewById<ImageView>(R.id.star2)
        val star3 = findViewById<ImageView>(R.id.star3)

        star1.setColorFilter(Color.argb(255, 105, 105, 105))
        star2.setColorFilter(Color.argb(255, 105, 105, 105))
        star3.setColorFilter(Color.argb(255, 105, 105, 105))

        fillStars(summ, star1, star2, star3, res)

        val recyclerView = findViewById<RecyclerView>(R.id.scoreRecyclerView)
        recyclerView.adapter = ScoreChangeRecyclerViewAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        //res.text = ((summ/4.0).toInt().toString() + "% правльных саженцев")
    }
    fun fillStars(summ:Int, star1: ImageView, star2: ImageView, star3: ImageView, resultTextView: TextView){
        if(summ<30){resultTextView.text = "Вы набрали "+(summ.toInt()).toString()+" баллов из 100! \n Вам явно нужно больше практики"}
        if (summ>=30){star1.setColorFilter(null); resultTextView.text = "Вы набрали "+(summ.toInt()).toString()+" баллов из 100! \n Стоит потренироваться ещё!"/*; FilterStart(star1)*/}
        if (summ>=50){star2.setColorFilter(null); resultTextView.text = "Вы набрали "+(summ.toInt()).toString()+" баллов из 100! \n Вы молодец, но нужно больше практики."/*; FilterStart(star2)*/}
        if (summ>=90){star3.setColorFilter(null); resultTextView.text = "Вы набрали "+(summ.toInt()).toString()+" баллов из 100! \n Вы большой молодец!"/*; FilterStart(star3)*/}
        if (summ>=100){resultTextView.text = "Вы набрали 100 баллов из 100 \n Идеально!"}
    }
}