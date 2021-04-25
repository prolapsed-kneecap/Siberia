package com.charkosoff.siberia.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.charkosoff.siberia.R
import com.charkosoff.siberia.data.Data

/**
 * A fragment representing a list of Items.
 */
val cultureNames = arrayOf("Овёс", "Пшеница", "Ячмень", "Горох", "Фасоль","Вспаханное поле")

class CultureFragment : Fragment() {

    private var columnCount = 1
    private lateinit var cultureRecyclerView: RecyclerView
    private var adapter: CultureAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_culture, container, false)

        cultureRecyclerView =
            view.findViewById(R.id.culture_recycler_view) as RecyclerView
        cultureRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()

        return view
    }

    private fun updateUI() {

        adapter = CultureAdapter(cultureNames)
        cultureRecyclerView.adapter = adapter
    }

    private inner class CultureHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        var cultureNameTextView: TextView = itemView.findViewById(R.id.culturesNameTextView)
        var cultureCardView: CardView = itemView.findViewById(R.id.cultureCardView)

        init {
            itemView.setOnClickListener(this)
        }


        fun bind(data: String) {
            cultureNameTextView.text = data
            cultureCardView.setOnClickListener {
                Data.currentCulture[Data.currentId] = data
                val res = when (data) {
                    "Овёс" -> R.drawable.oves
                    "Пшеница" -> R.drawable.pshenitsa
                    "Ячмень" -> R.drawable.yachmen
                    "Горох" -> R.drawable.goroh
                    "Фасоль" -> R.drawable.fasol
                    else -> R.drawable.field
                }
                val bundle = bundleOf(
                    "id" to Data.currentId,
                    "res" to res,
                    "name" to data
                )

                itemView.findNavController()
                  .navigate(R.id.action_navigation_culture_fragment_to_navigation_tech_viewpager_fragment, bundle)
            }
        }

        override fun onClick(v: View) {

        }
    }

    private inner class CultureAdapter(var cultures: Array<String>) :
        RecyclerView.Adapter<CultureHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : CultureHolder {
            val view = layoutInflater.inflate(R.layout.fragment_culture_list, parent, false)
            return CultureHolder(view)
        }

        override fun getItemCount() = cultures.size
        override fun onBindViewHolder(holder: CultureHolder, position: Int) {
            val culture = cultures[position]
            holder.apply {
                holder.bind(culture)
            }
        }
    }


    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(res: Int) =
            CultureFragment().apply {
                arguments = Bundle().apply {
                    putInt("res", res)
                }
            }
    }
}