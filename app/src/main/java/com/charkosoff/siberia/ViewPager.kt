package com.charkosoff.siberia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class ViewPager : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var id = arguments?.getInt("idKey")

        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)

        var viewPager = view.findViewById<ViewPager2>(R.id.viewPager2)


        viewPager.adapter = AdapterViewPager()


        var tabTitle = view.findViewById<TabItem>(R.id.Tab_title)

        var tabLayot = view.findViewById<TabLayout>(R.id.Tab_Layout)
        TabLayoutMediator(tabLayot, viewPager,
            TabLayoutMediator.TabConfigurationStrategy {tab, position ->
                when (position) {
                    0 -> { tab.text = "1"}
                    1 -> { tab.text = "2"}
                    2 -> { tab.text = "3"}
                    3 -> { tab.text = "4"}
                }
            }).attach()

        var tab:TabLayout.Tab = tabLayot.getTabAt(id!!)!!
        tab.select()

        return view
    }


}