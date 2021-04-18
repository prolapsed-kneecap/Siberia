package com.charkosoff.siberia.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charkosoff.siberia.R

class AdapterViewPagerCultures(names:Array<String>): RecyclerView.Adapter<PagerVHCultures>() {
    var cultureNames = names
    var tabs = arrayOf("1", "2")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVHCultures =
        PagerVHCultures(
            LayoutInflater.from(parent.context).inflate(
                R.layout.tab_cultures_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = tabs.size

    override fun onBindViewHolder(holder: PagerVHCultures, position: Int)=holder.itemView.run {
        holder.onBing(position, cultureNames)
    }

}

class PagerVHCultures(itemView: View) : RecyclerView.ViewHolder(itemView){
    var recyclerView:RecyclerView = itemView.findViewById(R.id.culturesRecyclerView)
    fun onBing(position: Int, names:Array<String>){
        var cultureNames = names
        when(position){
            0->{cultureNames = arrayOf("Зерновые","Овёс","Пшеница","Ячмень")}
            1->{cultureNames = arrayOf("Бобовые","Горох","Фасоль")}
        }
        recyclerView.adapter = RecyclerViewAdapterCultures(cultureNames)
        recyclerView.layoutManager = LinearLayoutManager(itemView.context)
    }
}