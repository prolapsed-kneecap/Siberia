package com.charkosoff.siberia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import androidx.core.widget.doOnTextChanged
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_view_pager)
        var viewPager = findViewById<ViewPager2>(R.id.viewPager2)


        viewPager.adapter = AdapterViewPager()


        var tabTitle = findViewById<TabItem>(R.id.Tab_title)

        var TabLayot = findViewById<TabLayout>(R.id.Tab_Layout)

        TabLayoutMediator(TabLayot, viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> { tab.text = "1"}
                    1 -> { tab.text = "2"}
                    2 -> { tab.text = "3"}
                    3 -> { tab.text = "4"}
                }
            }).attach()

    }
}


