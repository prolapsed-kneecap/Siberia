package com.charkosoff.siberia.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charkosoff.siberia.R

class AdapterViewPagerTech(names:Array<String>): RecyclerView.Adapter<PagerVHTech>() {
    var cultureNames = names
    var tabs = arrayOf("1", "2")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVHTech =
        PagerVHTech(
            LayoutInflater.from(parent.context).inflate(
                R.layout.tab_tech_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = tabs.size

    override fun onBindViewHolder(holder: PagerVHTech, position: Int)=holder.itemView.run {
        holder.onBing(position, cultureNames)
    }

}

class PagerVHTech(itemView: View) : RecyclerView.ViewHolder(itemView){
    var recyclerView:RecyclerView = itemView.findViewById(R.id.techRecyclerView)
    fun onBing(position: Int, names:Array<String>){
        var cultureNames = names
        when(position){
            0->{cultureNames = arrayOf("Сеялки","СЗП-3,6","СЗУ-Т-3.6","СЗУ-3,6-04")}
            1->{cultureNames = arrayOf("Комбайны","Енисей–1200–1НМ","Дон 1500")}
        }
        recyclerView.adapter = RecyclerViewAdapterTech(cultureNames)
        recyclerView.layoutManager = LinearLayoutManager(itemView.context)
    }
}