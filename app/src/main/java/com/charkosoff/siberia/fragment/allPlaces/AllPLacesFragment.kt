package com.charkosoff.siberia.fragment.allPlaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.charkosoff.siberia.R
import com.charkosoff.siberia.adapters.AdapterViewPager
import com.charkosoff.siberia.data.Data
import com.charkosoff.siberia.databinding.FragmentViewPagerBinding
import com.charkosoff.siberia.utils.Resource
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.viewmodel.ext.android.viewModel


class AllPLacesFragment : Fragment() {

    private lateinit var tab: TabLayout.Tab
    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewModel: AllPlacesViewModel by viewModel()

        val id = Data.currentId

        binding.moreFab.setOnClickListener {
            if (binding.techFab.visibility == View.GONE && binding.chemicalsFab.visibility == View.GONE) {
                binding.moreFab.setImageResource(R.drawable.ic_baseline_close_24)
                binding.techFab.visibility = View.VISIBLE
                binding.chemicalsFab.visibility = View.VISIBLE
            } else {
                binding.moreFab.setImageResource(R.drawable.ic_baseline_more_horiz_24)
                binding.techFab.visibility = View.GONE
                binding.chemicalsFab.visibility = View.GONE
            }
        }
        binding.chemicalsFab.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_viewpager_fragment_to_navigation_fertilizers_fragment)
        }

        binding.techFab.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_viewpager_fragment_to_navigation_culture_fragment)
        }

        binding.toMain.setOnClickListener{
            view.findNavController().popBackStack()
        }

        viewModel.times.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    Toast.makeText(view.context, "таймер кончился", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    binding.timerTextView.text = (it.data?.div(1000)).toString()

                }
            }
        }
        binding.timerTextView.setOnClickListener {
            viewModel.loadTime()
        }
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager2)
        viewPager.adapter = AdapterViewPager()

        val tabLayout = view.findViewById<TabLayout>(R.id.Tab_Layout)
        TabLayoutMediator(tabLayout, viewPager
        ) { tab, position ->
            tab.text = "ПОЛЕ ${(position + 1)}"
        }.attach()
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                Data.currentId = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        tab = tabLayout.getTabAt(id)!!
        tab.select()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}