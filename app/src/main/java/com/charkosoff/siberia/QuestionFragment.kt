package com.charkosoff.siberia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController

class QuestionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false)

        view.findViewById<CardView>(R.id.card_achiv).setOnClickListener {
            view.findNavController().navigate(R.id.action_questionFragment_to_achievementsFragment)
        }
        view.findViewById<CardView>(R.id.card_culture).setOnClickListener {
            view.findNavController().navigate(R.id.action_questionFragment_to_webSettingsFragment)
        }
        return view
    }
}