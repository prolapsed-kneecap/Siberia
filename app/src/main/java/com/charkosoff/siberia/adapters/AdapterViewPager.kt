package com.charkosoff.siberia.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.charkosoff.siberia.R
import com.charkosoff.siberia.data.Data
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AdapterViewPager : RecyclerView.Adapter<PagerVH>() {

    private val colors = intArrayOf(
        android.R.color.black,
        android.R.color.holo_red_light,
        android.R.color.holo_blue_dark,
        android.R.color.holo_purple
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
        PagerVH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.field_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = colors.size

    override fun onBindViewHolder(holder: PagerVH, position: Int) = holder.itemView.run {
        holder.onBing(position)
    }

}

class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView){
    var moreFab:FloatingActionButton = itemView.findViewById(R.id.moreFab)
    var techFab:FloatingActionButton = itemView.findViewById(R.id.techFab)
    var chemicalsFab:FloatingActionButton = itemView.findViewById(R.id.chemicalsFab)
    var currentCulture: TextView = itemView.findViewById(R.id.currentCulture)
    fun onBing(position: Int){
        currentCulture.text = Data.currentCulture
        moreFab.setOnClickListener {
            if (techFab.visibility==View.GONE && chemicalsFab.visibility==View.GONE){
                moreFab.setImageResource(R.drawable.ic_baseline_close_24)
                techFab.visibility = View.VISIBLE
                chemicalsFab.visibility = View.VISIBLE
            }
            else{
                moreFab.setImageResource(R.drawable.ic_baseline_more_horiz_24)
                techFab.visibility = View.GONE
                chemicalsFab.visibility = View.GONE
            }
        }
        techFab.setOnClickListener {
            itemView.findNavController().navigate(R.id.action_navigation_viewpager_fragment_to_navigation_tech_viewpager_fragment)
        }
    }
}