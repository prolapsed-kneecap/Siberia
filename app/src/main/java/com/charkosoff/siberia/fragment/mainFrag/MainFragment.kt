package com.charkosoff.siberia.fragment.mainFrag

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
import com.charkosoff.siberia.databinding.FragmentMainBinding
import com.charkosoff.siberia.fragment.cultureNames

class MainFragment : Fragment() {

    private val model = Model()


    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        val currentTime = model.getCurrent()
        val timeTable = model.timeTable()

        binding.monthTextView.text = model.getMonth(currentTime, timeTable)
        /*viewModel.loadTime()
        viewModel.times.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading-> {
                    monthTextView.text = model.getMonth(20000L - it.data!!, timeTable)
                }
            }
        }*/

        binding.field1.setOnClickListener { moveToField(view, 0) }
        binding.field2.setOnClickListener { moveToField(view, 1) }
        binding.field3.setOnClickListener { moveToField(view, 2) }
        binding.field4.setOnClickListener { moveToField(view, 3) }

        setCultureRes()

        return view
    }


    private fun moveToField(view: View, id: Int) {

        Data.currentId = id

        if (Data.currentCulture[id] == "Паровое поле")
            view.findNavController()
                .navigate(R.id.action_navigation_main_fragment_to_navigation_culture_fragment)
        else
            view.findNavController()
                .navigate(R.id.action_navigation_main_fragment_to_navigation_viewpager_fragment)
    }

    private fun setCultureRes() {
        val imagesViewToText = listOf(
            binding.field1 to binding.field1txt,
            binding.field2 to binding.field2txt,
            binding.field3 to binding.field3txt,
            binding.field4 to binding.field4txt,
        )
        for (i in imagesViewToText.indices)
            loadField(i, imagesViewToText[i].first, imagesViewToText[i].second)
    }

    private fun loadField(fieldId: Int, imageView: ImageView, textView: TextView) {
        val images = mapOf(
            cultureNames[0] to R.drawable.oves,
            cultureNames[1] to R.drawable.pshenitsa,
            cultureNames[2] to R.drawable.yachmen,
            cultureNames[3] to R.drawable.goroh,
            cultureNames[4] to R.drawable.fasol,
            cultureNames[5] to R.drawable.field
        )

        imageView.setImageResource(images[Data.currentCulture[fieldId]]!!)
        textView.text = Data.currentCulture[fieldId]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}