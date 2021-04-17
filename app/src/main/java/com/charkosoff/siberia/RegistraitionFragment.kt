package com.charkosoff.siberia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class RegistraitionFragment: Fragment() {

    private val model = Model(this.requireContext())


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(this.requireContext()).inflate(R.layout.fragment_registraition, container)

        var currentTime = model.getCurrent()
        var timeTable = model.timeTable()

        val nameEditText: EditText = view.findViewById(R.id.editTextName)
        val groupEditText: EditText = view.findViewById(R.id.editTextGroup)
        val inButton: Button = view.findViewById(R.id.button)

        val user = User("Имя", "Группа")

        nameEditText.doOnTextChanged { text, start, before, count ->
            user.name = text.toString()
        }
        groupEditText.doOnTextChanged { text, start, before, count ->
            user.group = text.toString()
        }
        inButton.setOnClickListener {
            findNavController().navigate(R.id.navigation_viewpager_fragment)
        }

        return view
    }
}