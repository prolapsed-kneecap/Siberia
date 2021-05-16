package com.charkosoff.siberia.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.charkosoff.siberia.R
import com.charkosoff.siberia.classes.Achievements

class AchievementsRecyclerViewAdapter: RecyclerView.Adapter<AchievementsRecyclerViewAdapter.MyViewHolder>(){
    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val nameTextView = itemView.findViewById<TextView>(R.id.name_achivka3)
        val problemTextView = itemView.findViewById<TextView>(R.id.achiv_problem3)
        val achivImageView = itemView.findViewById<ImageView>(R.id.image_achivka3)
        fun onBing(position: Int){
            nameTextView.text = Achievements.achievements[position].achivName
            problemTextView.text = Achievements.achievements[position].achivTask
            achivImageView.setImageResource(Achievements.achievements[position].image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.achievement_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBing(position)
    }

    override fun getItemCount(): Int = Achievements.achievements.size
}