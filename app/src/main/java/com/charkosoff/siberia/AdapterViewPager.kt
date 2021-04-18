package com.charkosoff.siberia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AdapterViewPager : RecyclerView.Adapter<PagerVH>() {

    private val colors = intArrayOf(
        android.R.color.black,
        android.R.color.holo_red_light,
        android.R.color.holo_blue_dark,
        android.R.color.holo_purple
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
        PagerVH(LayoutInflater.from(parent.context).inflate(R.layout.field_item, parent, false))

    override fun getItemCount(): Int = colors.size

    override fun onBindViewHolder(holder: PagerVH, position: Int) = holder.itemView.run {
        var moreFab:FloatingActionButton = findViewById(R.id.moreFab)
        var techFab:FloatingActionButton = findViewById(R.id.techFab)
        var chemicalsFab:FloatingActionButton = findViewById(R.id.chemicalsFab)
        holder.onBing(position)
    }

}

class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView){
    var moreFab:FloatingActionButton = itemView.findViewById(R.id.moreFab)
    var techFab:FloatingActionButton = itemView.findViewById(R.id.techFab)
    var chemicalsFab:FloatingActionButton = itemView.findViewById(R.id.chemicalsFab)
    fun onBing(position: Int){
        moreFab.setOnClickListener {
            techFab.visibility = View.VISIBLE
            chemicalsFab.visibility = View.VISIBLE
        }
    }
}