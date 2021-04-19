package com.charkosoff.siberia.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.charkosoff.siberia.R
import com.charkosoff.siberia.adapters.AdapterViewPager
import com.charkosoff.siberia.data.Data
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class ViewPagerFragment : Fragment() {
    lateinit var tab: TabLayout.Tab
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var id = Data.currentId
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)

        var moreFab: FloatingActionButton = view.findViewById(R.id.moreFab)
        var techFab: FloatingActionButton = view.findViewById(R.id.techFab)
        var chemicalsFab: FloatingActionButton = view.findViewById(R.id.chemicalsFab)

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
        techFab.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_viewpager_fragment_to_navigation_tech_viewpager_fragment)
        }
        var viewPager = view.findViewById<ViewPager2>(R.id.viewPager2)
        viewPager.adapter = AdapterViewPager()

        var tabLayot = view.findViewById<TabLayout>(R.id.Tab_Layout)
        TabLayoutMediator(tabLayot, viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> {
                        tab.text = "1"
                    }
                    1 -> {
                        tab.text = "2"
                    }
                    2 -> {
                        tab.text = "3"
                    }
                    3 -> {
                        tab.text = "4"
                    }
                }
            }).attach()
        tabLayot.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                Data.currentId = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        tab = tabLayot.getTabAt(id)!!
        tab.select()

        return view
    }


}