package com.charkosoff.siberia.fragment.allPlaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.charkosoff.siberia.R
import com.charkosoff.siberia.adapters.AdapterViewPager
import com.charkosoff.siberia.data.Data
import com.charkosoff.siberia.utils.Resource
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.viewmodel.ext.android.viewModel


class AllPLacesFragment : Fragment() {

    private lateinit var tab: TabLayout.Tab


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewModel: AllPlacesViewModel by viewModel()

        val id = Data.currentId
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)

        val moreFab: FloatingActionButton = view.findViewById(R.id.moreFab)
        val techFab: FloatingActionButton = view.findViewById(R.id.techFab)
        val chemicalsFab: FloatingActionButton = view.findViewById(R.id.chemicalsFab)
        val timerTextView: TextView = view.findViewById(R.id.timerTextView)
        val toMain: FloatingActionButton = view.findViewById(R.id.toMain)

        moreFab.setOnClickListener {
            if (techFab.visibility == View.GONE && chemicalsFab.visibility == View.GONE) {
                moreFab.setImageResource(R.drawable.ic_baseline_close_24)
                techFab.visibility = View.VISIBLE
                chemicalsFab.visibility = View.VISIBLE
            } else {
                moreFab.setImageResource(R.drawable.ic_baseline_more_horiz_24)
                techFab.visibility = View.GONE
                chemicalsFab.visibility = View.GONE
            }
        }
        chemicalsFab.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_viewpager_fragment_to_navigation_fertilizers_fragment)
        }

        techFab.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_viewpager_fragment_to_navigation_culture_fragment)
        }

        toMain.setOnClickListener{
            view.findNavController().popBackStack()
        }

        viewModel.times.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    Toast.makeText(view.context, "таймер кончился", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    timerTextView.text = (it.data?.div(1000)).toString()

                }
            }
        }
        timerTextView.setOnClickListener {
            viewModel.loadTime()
        }
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager2)
        viewPager.adapter = AdapterViewPager()

        val tabLayout = view.findViewById<TabLayout>(R.id.Tab_Layout)
        TabLayoutMediator(tabLayout, viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = "ПОЛЕ ${(position + 1)}"
            }).attach()
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


}