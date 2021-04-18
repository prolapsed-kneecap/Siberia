package com.charkosoff.siberia.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.charkosoff.siberia.R

class RecyclerViewAdapterTech(values:Array<String>):
    RecyclerView.Adapter<RecyclerViewAdapterTech.TechViewHolder>() {

    var data = values
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TechViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.tech_item, parent, false)
        return TechViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TechViewHolder, position: Int) {
        holder.onBing(position)
    }
    inner class TechViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var techNameTextView: TextView = itemView.findViewById(R.id.techNameTextView)
        var techCardView: CardView = itemView.findViewById(R.id.techCardView)
        fun onBing(position:Int){
            techNameTextView.text = data[position]
            techCardView.setOnClickListener {
                itemView.findNavController().navigate(R.id.action_navigation_tech_viewpager_fragment_to_navigation_cultures_viewpager_fragment)
            }
        }
    }
}