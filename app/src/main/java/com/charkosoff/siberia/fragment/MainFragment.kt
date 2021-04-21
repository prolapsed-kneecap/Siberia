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


private const val TAG = "MainFragment"

class MainFragment : Fragment() {

    private val model = Model()
    private lateinit var firstField: ImageView
    private lateinit var secondField: ImageView
    private lateinit var thirdField: ImageView
    private lateinit var fourthField: ImageView

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
        firstField = view.findViewById(R.id.field1)
        secondField = view.findViewById(R.id.field2)
        thirdField = view.findViewById(R.id.field3)
        fourthField = view.findViewById(R.id.field4)

        monthTextView.text = model.getMonth(currentTime, timeTable)

        firstField.setOnClickListener { moveToField(view, 0) }
        secondField.setOnClickListener { moveToField(view, 1) }
        thirdField.setOnClickListener { moveToField(view, 2) }
        fourthField.setOnClickListener { moveToField(view, 3) }

        return view
    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onResume() called")
    }


    private fun moveToField(view: View, id: Int) {
        val bundle = Bundle()
        Data.currentId = id
        view.findNavController()
            .navigate(R.id.action_navigation_main_fragment_to_navigation_viewpager_fragment, bundle)
    }
}