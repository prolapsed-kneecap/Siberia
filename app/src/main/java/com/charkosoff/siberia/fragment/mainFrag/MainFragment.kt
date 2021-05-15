package com.charkosoff.siberia.fragment.mainFrag

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.charkosoff.siberia.Model
import com.charkosoff.siberia.R
import com.charkosoff.siberia.data.Data
import com.charkosoff.siberia.data.Data.currentTime
import com.charkosoff.siberia.data.PlayButton
import com.charkosoff.siberia.databinding.FragmentMainBinding
import com.charkosoff.siberia.fragment.cultureNames
import com.charkosoff.siberia.ResultedActivity
import com.charkosoff.siberia.data.Data.globalTimerIsRunning
import com.charkosoff.siberia.utils.Resource
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private val model = Model()
    val viewModel:MainFragmentViewModel by viewModel()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        var currentTime = model.getCurrent()
        var currentGlobalTime = model.getGlobalCurrent()
        val timeTable = model.timeTable()

        binding.field1.setOnClickListener { moveToField(view, 0) }
        binding.field2.setOnClickListener { moveToField(view, 1) }
        binding.field3.setOnClickListener { moveToField(view, 2) }
        binding.field4.setOnClickListener { moveToField(view, 3) }

        viewModel.globalTimes.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading->{
                    currentGlobalTime= it.data!!
                    binding.timerTestTextView.text = (it.data).toString()+" global timer"
                    binding.monthTextView.text = model.getMonth(it.data, timeTable)
                    binding.eventTextViewForTests.text = model.getCurrentEvent()+" (for tests)"
                    Data.currentMonth = model.getMonth(it.data, timeTable)
                    Data.currentTime = it.data
                    Data.currentEvent = model.getCurrentEvent()
                }
                is Resource.Success->{
                    var result = Intent(activity, ResultedActivity::class.java)
                    startActivity(result)
                }
            }
        }

        viewModel.times.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading-> {
                    currentTime=it.data!!
                    binding.timerTestTextView.text = (it.data).toString()+" test timer"
                    binding.monthTextView.text = model.getMonth(it.data, timeTable)

                }
                is Resource.Success ->{
                    val result = Intent(activity,ResultedActivity::class.java)
                    startActivity(result)
                    binding.timerTestTextView.isClickable = true
                }
            }
        }

        binding.speedFab.setOnClickListener {
            PlayButton.isSpeeded=!PlayButton.isSpeeded
            if (PlayButton.isSpeeded)
                binding.speedFab.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            else
                binding.speedFab.setImageResource(R.drawable.ic_baseline_fast_forward_24)
        }
        binding.timerTestTextView.setOnClickListener {
            binding.timerTestTextView.isClickable=false
            if(!globalTimerIsRunning){
                viewModel.loadGlobalTime()
                globalTimerIsRunning=true
            }
        }

        setCultureRes()

        return view
    }


    private fun moveToField(view: View, id: Int) {

        Data.currentId = id

        if (Data.currentCulture[id] == "Паровое поле"){
            if(!globalTimerIsRunning){
                globalTimerIsRunning=true
                viewModel.loadGlobalTime()
            }
            viewModel.globalTimes.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                        currentTime = it.data!!
                    }
                    is Resource.Success -> {
                        globalTimerIsRunning=false
                        binding.timerTestTextView.isClickable = true
                        val resultAct = Intent(activity,ResultedActivity::class.java)
                        startActivity(resultAct)
                    }
                }
            }
            view.findNavController()
                .navigate(R.id.action_navigation_main_fragment_to_navigation_culture_fragment)
        }
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