package com.charkosoff.siberia.fragment.mainFrag

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.charkosoff.siberia.Model
import com.charkosoff.siberia.R
import com.charkosoff.siberia.ResultedActivity
import com.charkosoff.siberia.data.Data
import com.charkosoff.siberia.data.PlayButton
import com.charkosoff.siberia.databinding.FragmentMainBinding
import com.charkosoff.siberia.fragment.cultureNames
import com.charkosoff.siberia.utils.Resource
import com.charkosoff.siberia.utils.StatusUtils
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetSequence
import org.koin.android.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    private val model = Model()
    private val viewModel: MainFragmentViewModel by viewModel()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        showToolTip()
        var currentTime = model.getCurrent()
        val timeTable = model.timeTable()

        binding.field1.setOnClickListener { moveToField(view, 0) }
        binding.field2.setOnClickListener { moveToField(view, 1) }
        binding.field3.setOnClickListener { moveToField(view, 2) }
        binding.field4.setOnClickListener { moveToField(view, 3) }
        val animator = binding.viewAnimator


        val animationIn = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left)


        val animationOut = AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right)


        animationOut.duration = 10000
        animationIn.duration = 10000

        binding.speedFabPause.setOnClickListener {
            if(!Data.globalTimerWasStarted)
                viewModel.loadGlobalTime()
                Data.globalTimerWasStarted = true
            if(Data.globalTimerIsRunning) {
                binding.speedFabPause.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                Data.globalTimerIsRunning = false
                Data.globalTimerIsStopped = true
                binding.speedFab.isEnabled = false
            }
            else {
                binding.speedFabPause.setImageResource(R.drawable.ic_baseline_pause_24)
                Data.globalTimerIsRunning = true
                Data.globalTimerIsStopped = false
                binding.speedFab.isEnabled = true
            }
        }

        animator.inAnimation = animationIn
        animator.outAnimation = animationOut
        viewModel.globalTimes.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    currentTime = it.data!!
                    binding.timerTestTextView.text = (it.data).toString() + " test timer"
                    binding.monthTextView.text = model.getMonth(it.data, timeTable)
                    Data.currentMonth = model.getMonth(it.data, timeTable)
                    Data.currentTime = it.data
                    Data.currentEvent = model.getCurrentEvent()

                }
                is Resource.Success -> {
                    val result = Intent(activity, ResultedActivity::class.java)
                    // startActivity(result)


                }
            }
        }

        binding.settingsButtons.setOnClickListener{
            view.findNavController().navigate(R.id.action_navigation_main_fragment_to_webSettingsFragment)
        }

        binding.speedFab.setOnClickListener {
            PlayButton.isSpeeded = !PlayButton.isSpeeded
            if (PlayButton.isSpeeded)
                binding.speedFab.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            else
                binding.speedFab.setImageResource(R.drawable.ic_baseline_fast_forward_24)
        }
        binding.timerTestTextView.setOnClickListener {
            viewModel.loadGlobalTime()
        }

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

    private fun showToolTip() {

        if (StatusUtils.getStatusMain(requireContext())) {
            TapTargetSequence(activity)
                .targets(
                    TapTarget.forView(binding.field1txt, "Нажмите на поле, чтобы посадить культуры")
                        .cancelable(false).transparentTarget(true).targetRadius(70)
                        .tintTarget(false),
                    TapTarget.forView(
                        binding.speedFab,
                        "Нажмите на кнопку, чтобы начать отчет времени"
                    )
                        .cancelable(false).transparentTarget(true).targetRadius(70)
                        .tintTarget(true),
                    TapTarget.forView(binding.monthTextView, "Здесь отображается текущий меся года")
                        .cancelable(false).transparentTarget(true).targetRadius(70)
                        .tintTarget(false),
                    TapTarget.forView(binding.settingsButtons, "Здесь вы найдете справку по игре!")
                        .cancelable(false).transparentTarget(true).targetRadius(70)
                        .tintTarget(false)
                ).listener(object : TapTargetSequence.Listener {
                    override fun onSequenceStep(lastTarget: TapTarget?, targetClicked: Boolean) {
                    }

                    override fun onSequenceFinish() {
                        StatusUtils.storeStatusMain(requireContext(), false)
                    }

                    override fun onSequenceCanceled(lastTarget: TapTarget) {
                    }
                }).start()

        }
    }
}