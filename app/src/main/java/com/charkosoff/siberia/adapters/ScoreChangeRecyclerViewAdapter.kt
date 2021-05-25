package com.charkosoff.siberia.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.charkosoff.siberia.R
import com.charkosoff.siberia.ScoreChanges
import org.w3c.dom.Text

class ScoreChangeRecyclerViewAdapter:
    RecyclerView.Adapter<ScoreChangeRecyclerViewAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val descTextView = itemView.findViewById<TextView>(R.id.descTextView)
        val plusTextView = itemView.findViewById<TextView>(R.id.plusTextView)
        val checkTextView = itemView.findViewById<TextView>(R.id.check)
        fun onBing(position: Int){
            val change = ScoreChanges.scoreChanges[position]
            checkTextView.text = change.check.toString()
            plusTextView.text = change.plus.toString()
            descTextView.text = change.desc
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.score_change_recycler_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBing(position)
    }

    override fun getItemCount(): Int = ScoreChanges.scoreChanges.size

}