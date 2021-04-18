package com.charkosoff.siberia.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.charkosoff.siberia.R
import com.charkosoff.siberia.adapters.AdapterViewPager
import com.charkosoff.siberia.data.Data
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class ViewPagerFragment : Fragment() {
    lateinit var tab:TabLayout.Tab
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var id = arguments?.getInt("idKey")
        //val id2 = arguments?.getInt("id2Key")
        val position = arguments?.getInt("keyPos")

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
        if (id!=null){
            tab = tabLayot.getTabAt(id)!!
        }
        else{
            id = Data.id
            tab = tabLayot.getTabAt(id)!!
        }
        tab.select()

        return view
    }


}