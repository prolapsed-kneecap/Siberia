package com.charkosoff.siberia.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.charkosoff.siberia.R
import com.charkosoff.siberia.data.Data


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

class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var currentCulture: TextView = itemView.findViewById(R.id.currentCulture)
    private var currentCultureImage: ImageView = itemView.findViewById(R.id.imageView)


    fun onBing(position: Int) {
        currentCulture.text = Data.currentCulture[position]
        when (Data.currentCulture[position]) {
            "Овёс" -> currentCultureImage.setImageResource(R.drawable.oves)
            "Пшеница" -> currentCultureImage.setImageResource(R.drawable.pshenitsa)
            "Ячмень" -> currentCultureImage.setImageResource(R.drawable.yachmen)
            "Горох" -> currentCultureImage.setImageResource(R.drawable.goroh)
            "Фасоль" -> currentCultureImage.setImageResource(R.drawable.fasol)

        }
    }
}