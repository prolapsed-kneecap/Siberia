package com.charkosoff.siberia.fragment

import android.os.Bundle
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

class MainFragment: Fragment() {

    private val model = Model()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(this.requireContext()).inflate(R.layout.fragment_main, container, false)

        var currentTime = model.getCurrent()
        val timeTable = model.timeTable()

        val monthTextView: TextView = view.findViewById(R.id.monthTextView)
        val firstField:ImageView = view.findViewById(R.id.field1)
        val secondField:ImageView = view.findViewById(R.id.field2)
        val thirdField:ImageView = view.findViewById(R.id.field3)
        val fourthField:ImageView = view.findViewById(R.id.field4)

        monthTextView.text = model.getMonth(currentTime, timeTable)

        firstField.setOnClickListener {moveToField(view, 0)}
        secondField.setOnClickListener {moveToField(view, 1)}
        thirdField.setOnClickListener {moveToField(view, 2)}
        fourthField.setOnClickListener {moveToField(view, 3)}



        return view
    }
    private fun moveToField(view:View, id:Int){
        val bundle = Bundle()
        Data.currentId=id
        view.findNavController().navigate(R.id.action_navigation_main_fragment_to_navigation_viewpager_fragment, bundle)
    }
}