package com.charkosoff.siberia.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.charkosoff.siberia.R
import com.charkosoff.siberia.User

class RegistraitionFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registraition, container, false)



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
            findNavController(this).navigate(R.id.action_navigation_registration_fragment_to_navigation_main_fragment)
        }

        return view
    }
}