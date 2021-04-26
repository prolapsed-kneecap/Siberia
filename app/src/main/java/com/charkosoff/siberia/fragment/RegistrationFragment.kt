package com.charkosoff.siberia.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.charkosoff.siberia.R
import com.charkosoff.siberia.User
import com.charkosoff.siberia.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        val view = binding.root


        val user = User("Имя", "Группа")

        binding.editTextName.doOnTextChanged { text, start, before, count ->
            user.name = text.toString()
        }
        binding.editTextGroup.doOnTextChanged { text, start, before, count ->
            user.group = text.toString()
        }
        binding.button.setOnClickListener {
            findNavController(this).navigate(R.id.action_navigation_registration_fragment_to_navigation_main_fragment)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}