package com.charkosoff.siberia

import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment: Fragment() {

    private val model = Model()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(this.requireContext()).inflate(R.layout.fragment_main, container, false)

        var currentTime = model.getCurrent()
        var timeTable = model.timeTable()

        var monthTextView: TextView = view.findViewById(R.id.monthTextView)
        var firstField:ImageView = view.findViewById(R.id.field1)
        var secondField:ImageView = view.findViewById(R.id.field2)
        var thirdField:ImageView = view.findViewById(R.id.field3)
        var fourthField:ImageView = view.findViewById(R.id.field4)

        monthTextView.text = model.getMonth(currentTime, timeTable)

        firstField.setOnClickListener {moveToField(view, 0)}
        secondField.setOnClickListener {moveToField(view, 1)}
        thirdField.setOnClickListener {moveToField(view, 2)}
        fourthField.setOnClickListener {moveToField(view, 3)}



        return view
    }
    fun moveToField(view:View, id:Int){
        val bundle = Bundle()
        bundle.putInt("idKey", id)
        view.findNavController().navigate(R.id.action_navigation_main_fragment_to_navigation_viewpager_fragment, bundle)
    }
}