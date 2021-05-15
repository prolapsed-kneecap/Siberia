package com.charkosoff.siberia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.charkosoff.siberia.R
import com.charkosoff.siberia.data.Data
import com.charkosoff.siberia.databinding.FieldItemBinding


class AdapterViewPager : RecyclerView.Adapter<PagerVH>() {

    private val colors = intArrayOf(
        android.R.color.black,
        android.R.color.holo_red_light,
        android.R.color.holo_blue_dark,
        android.R.color.holo_purple
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val fieldItemBinding = FieldItemBinding.inflate(layoutInflater, parent, false)
        return PagerVH(fieldItemBinding)
    }

    override fun getItemCount(): Int = colors.size

    override fun onBindViewHolder(holder: PagerVH, position: Int) = holder.itemView.run {
        holder.onBing(position)
    }
}

class PagerVH(private val fieldItemBinding: FieldItemBinding) : RecyclerView.ViewHolder(fieldItemBinding.root) {
    val eventTextView = itemView.findViewById<TextView>(R.id.eventTextView)
    fun onBing(position: Int) {
        fieldItemBinding.currentCulture.text = Data.currentCulture[position]
        eventTextView.text = Data.currentEvent
        when (Data.currentCulture[position]) {
            "Овёс" -> fieldItemBinding.imageView.setImageResource(R.drawable.oves)
            "Пшеница" -> fieldItemBinding.imageView.setImageResource(R.drawable.pshenitsa)
            "Ячмень" -> fieldItemBinding.imageView.setImageResource(R.drawable.yachmen)
            "Горох" -> fieldItemBinding.imageView.setImageResource(R.drawable.goroh)
            "Фасоль" -> fieldItemBinding.imageView.setImageResource(R.drawable.fasol)
        }
    }
}