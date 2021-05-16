package com.charkosoff.siberia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charkosoff.siberia.adapters.AchievementsRecyclerViewAdapter


class AchievementsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_achivments, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.achievemnets_recycler)

        recyclerView.adapter = AchievementsRecyclerViewAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return view
    }


                }


