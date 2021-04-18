package com.charkosoff.siberia.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.charkosoff.siberia.R
import com.charkosoff.siberia.data.Data

class RecyclerViewAdapterCultures(values:Array<String>):
    RecyclerView.Adapter<RecyclerViewAdapterCultures.MyViewHolder>() {

    var data = values
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.culture_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBing(position)
    }
    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var cultureNameTextView:TextView = itemView.findViewById(R.id.culturesNameTextView)
        var cultureCardView:CardView = itemView.findViewById(R.id.cultureCardView)
        fun onBing(position:Int){
            cultureNameTextView.text = data[position]
            cultureCardView.setOnClickListener {
                var bundle = Bundle()
                bundle.putInt("keyPos", position)
                Data.currentCulture = arrayOf("Зерновые","Овёс","Пшеница","Ячмень")[position]
                //bundle.putInt("id2Key", )
                itemView.findNavController().navigate(R.id.action_navigation_cultures_viewpager_fragment_to_navigation_viewpager_fragment, bundle)
            }
        }
    }
}