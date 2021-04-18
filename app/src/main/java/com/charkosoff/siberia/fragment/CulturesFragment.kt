package com.charkosoff.siberia.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.charkosoff.siberia.R
import com.charkosoff.siberia.adapters.AdapterViewPagerCultures
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CulturesFragment:Fragment() {
    var names = arrayOf<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //var id = arguments?.getInt("idKey")

        val view = inflater.inflate(R.layout.fragment_cultures, container, false)

        var viewPager = view.findViewById<ViewPager2>(R.id.viewPagerCultures)

        viewPager.adapter = AdapterViewPagerCultures(names)


        var tabTitle = view.findViewById<TabItem>(R.id.tabTitle)

        var tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)

        TabLayoutMediator(tabLayout, viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> { tab.text = "зерновые"/*; names = arrayOf("Овёс","Пшеница","Ячмень")*/}
                    1 -> { tab.text = "бобовые"/*; names = arrayOf("Горох","Фасоль")*/}
                }
            }).attach()

        return view
    }


}