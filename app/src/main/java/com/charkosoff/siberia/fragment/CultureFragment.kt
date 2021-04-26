package com.charkosoff.siberia.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charkosoff.siberia.R
import com.charkosoff.siberia.data.Data
import com.charkosoff.siberia.data.Data.culturesToShow
import com.charkosoff.siberia.databinding.FragmentCultureListBinding

/**
 * A fragment representing a list of Items.
 */
val cultureNames = arrayOf("Овёс", "Пшеница", "Ячмень", "Горох", "Фасоль", "Вспаханное поле")

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

        adapter = CultureAdapter()
        cultureRecyclerView.adapter = adapter
    }

    private inner class CultureHolder(private val cultureItemBinding: FragmentCultureListBinding) :
        RecyclerView.ViewHolder(cultureItemBinding.root),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }


        fun bind(data: String) {
            cultureItemBinding.culturesNameTextView.text = data
            cultureItemBinding.cultureCardView.setOnClickListener {
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
                    .navigate(
                        R.id.action_navigation_culture_fragment_to_navigation_tech_viewpager_fragment,
                        bundle
                    )
            }
        }

        override fun onClick(v: View) {

        }
    }

    private inner class CultureAdapter() :
        RecyclerView.Adapter<CultureHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : CultureHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val cultureItemBinding = FragmentCultureListBinding.inflate(layoutInflater, parent, false)
            return CultureHolder(cultureItemBinding)
        }

        override fun getItemCount() = culturesToShow.size
        override fun onBindViewHolder(holder: CultureHolder, position: Int) {
            val culture = culturesToShow[position]
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