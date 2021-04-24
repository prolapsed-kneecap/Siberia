package com.charkosoff.siberia.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.charkosoff.siberia.Model
import com.charkosoff.siberia.R
import com.charkosoff.siberia.data.Data

class MainFragment : Fragment() {

    private val model = Model()
    private lateinit var firstField: ImageView
    private lateinit var secondField: ImageView
    private lateinit var thirdField: ImageView
    private lateinit var fourthField: ImageView
    private lateinit var firstFieldTxt: TextView
    private lateinit var secondFieldTxt: TextView
    private lateinit var thirdFieldTxt: TextView
    private lateinit var fourthFieldTxt: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(this.requireContext())
            .inflate(R.layout.fragment_main, container, false)

        val currentTime = model.getCurrent()
        val timeTable = model.timeTable()

        val monthTextView: TextView = view.findViewById(R.id.monthTextView)
        firstField = view.findViewById(R.id.field1) as ImageView
        secondField = view.findViewById(R.id.field2) as ImageView
        thirdField = view.findViewById(R.id.field3) as ImageView
        fourthField = view.findViewById(R.id.field4) as ImageView
        firstFieldTxt = view.findViewById(R.id.field1txt) as TextView
        secondFieldTxt = view.findViewById(R.id.field2txt) as TextView
        thirdFieldTxt = view.findViewById(R.id.field3txt) as TextView
        fourthFieldTxt = view.findViewById(R.id.field4txt) as TextView

        monthTextView.text = model.getMonth(currentTime, timeTable)

        firstField.setOnClickListener { moveToField(view, 0) }
        secondField.setOnClickListener { moveToField(view, 1) }
        thirdField.setOnClickListener { moveToField(view, 2) }
        fourthField.setOnClickListener { moveToField(view, 3) }

        setCultureRes()

        return view
    }


    private fun moveToField(view: View, id: Int) {

        Data.currentId = id

        if (Data.currentCulture[id] == "Вспаханное поле")
            view.findNavController()
                .navigate(R.id.action_navigation_main_fragment_to_navigation_culture_fragment)
        else
            view.findNavController()
                .navigate(R.id.action_navigation_main_fragment_to_navigation_viewpager_fragment)
    }

    private fun setCultureRes() {

        val res = arguments?.getInt("res")
        val name = arguments?.getString("name")
        when (arguments?.getInt("id")) {
            0 -> {
                if (res != null) {
                    firstField.setImageResource(res)
                }
                firstFieldTxt.text = name
            }
            1 -> {
                if (res != null) {
                    secondField.setImageResource(res)
                }
                secondFieldTxt.text = name
            }
            2 -> {
                if (res != null) {
                    thirdField.setImageResource(res)
                }
                thirdFieldTxt.text = name
            }
            3 -> {
                if (res != null) {
                    fourthField.setImageResource(res)
                }
                fourthFieldTxt.text = name
            }
        }
    }

}