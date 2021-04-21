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
import androidx.navigation.findNavController
import com.charkosoff.siberia.R

/**
 * A fragment representing a list of Items.
 */
class TechsFragment : Fragment() {

    private var columnCount = 1
    private var techNames =
        arrayOf("СЗП-3,6", "СЗУ-Т-3.6", "СЗУ-3,6-04", "Енисей–1200–1НМ", "Дон 1500")
    private lateinit var techRecyclerView: RecyclerView
    private var adapter: TechAdapter? = null

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
        val view = inflater.inflate(R.layout.fragment_techs, container, false)

        techRecyclerView =
            view.findViewById(R.id.tech_recycler_view) as RecyclerView
        techRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()

        return view
    }

    private fun updateUI() {

        adapter = TechAdapter(techNames)
        techRecyclerView.adapter = adapter
    }

    private inner class TechHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        var techNameTextView: TextView = itemView.findViewById(R.id.techNameTextView)
        var techCardView: CardView = itemView.findViewById(R.id.techCardView)

        init {
            itemView.setOnClickListener(this)
        }


        fun bind(data: String) {
            techNameTextView.text = data
            techCardView.setOnClickListener {
                itemView.findNavController().popBackStack()
            }
        }

        override fun onClick(v: View) {

        }


    }

    private inner class TechAdapter(var techs: Array<String>) : RecyclerView.Adapter<TechHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : TechHolder {
            val view = layoutInflater.inflate(R.layout.fragment_techs_list, parent, false)
            return TechHolder(view)
        }

        override fun getItemCount() = techs.size
        override fun onBindViewHolder(holder: TechHolder, position: Int) {
            val tech = techs[position]
            holder.apply {
                holder.bind(tech)
            }
        }
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            TechsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}