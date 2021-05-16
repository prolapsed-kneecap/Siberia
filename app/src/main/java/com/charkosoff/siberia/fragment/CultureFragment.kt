package com.charkosoff.siberia.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charkosoff.siberia.CultureMaster
import com.charkosoff.siberia.R
import com.charkosoff.siberia.classes.ListOfFields
import com.charkosoff.siberia.classes.Plants
import com.charkosoff.siberia.data.Data
import com.charkosoff.siberia.data.Data.culturesToShow
import com.charkosoff.siberia.databinding.FragmentCultureBinding
import com.charkosoff.siberia.databinding.FragmentCultureListBinding
import java.util.*
import kotlin.collections.ArrayList

/**
 * A fragment representing a list of Items.
 */
private const val TAG = "CultureFragment"
val cultureNames = arrayListOf("Овёс", "Пшеница", "Ячмень", "Горох", "Фасоль", "Паровое поле")

class CultureFragment : Fragment() {

    private var columnCount = 1
    private var adapter: CultureAdapter? = null
    private var _binding: FragmentCultureBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCultureBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.cultureRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter?.filter?.filter(newText)
                return false
            }

        })

        return view
    }

    private fun updateUI() {

        adapter = CultureAdapter()
        binding.cultureRecyclerView.adapter = adapter
    }

    private inner class CultureHolder(private val cultureItemBinding: FragmentCultureListBinding) :
        RecyclerView.ViewHolder(cultureItemBinding.root),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }


        @SuppressLint("ShowToast", "SetTextI18n")
        fun bind(data: String, position: Int) {
            cultureItemBinding.culturesNameTextView.text = data
            val selectedCulture = Plants.cultures[position]
            val selectedField = ListOfFields.fields[Data.currentId]

            cultureItemBinding.description.setText(selectedCulture.description)
            cultureItemBinding.cultureRes.setImageResource(selectedCulture.image)
            cultureItemBinding.family.text ="Семейство: "+selectedCulture.family

            itemView.setOnClickListener {
                Data.currentCulture[Data.currentId] = data
                selectedField.userSequenceCulture.add(data)
                if (selectedField.userSequenceCulture.size > 1){
                    val check = CultureMaster()
                    if (check.howIsGoodChoice(selectedField.userSequenceCulture[selectedField.userSequenceCulture.size - 2],
                        selectedField.userSequenceCulture[selectedField.userSequenceCulture.size - 1])) {
                        Log.i(TAG, "Правильно наследовал")
                        selectedField.score++
                    } else
                    {
                        Log.i(TAG, "неправильно наследовал")
                    }
                }
                Log.i(TAG, "Выбраная культура ${selectedCulture.name},id field: ${Data.currentId} sequence: ${selectedField.userSequenceCulture}")
                itemView.findNavController()
                    .navigate(
                        R.id.action_navigation_culture_fragment_to_navigation_tech_viewpager_fragment
                    )
            }
        }

        override fun onClick(v: View) {

        }
    }

    private inner class CultureAdapter() :
        RecyclerView.Adapter<CultureHolder>(), Filterable {

        var cultureFilterList = ArrayList<String>()

        init {
            cultureFilterList = culturesToShow
        }

        override fun getFilter(): Filter {
            return object : Filter() {
                override fun performFiltering(constraint: CharSequence?): FilterResults {
                    val charSearch = constraint.toString()
                    cultureFilterList = if (charSearch.isEmpty()) {
                        culturesToShow
                    } else {
                        val resultList = ArrayList<String>()
                        for (row in culturesToShow) {
                            if (row.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                                resultList.add(row)
                            }
                        }
                        resultList
                    }
                    val filterResults = FilterResults()
                    filterResults.values = cultureFilterList
                    return filterResults
                }

                @Suppress("UNCHECKED_CAST")
                override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                    cultureFilterList = results?.values as ArrayList<String>
                    notifyDataSetChanged()
                }

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : CultureHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val cultureItemBinding =
                FragmentCultureListBinding.inflate(layoutInflater, parent, false)
            return CultureHolder(cultureItemBinding)
        }

        override fun getItemCount() = cultureFilterList.size
        override fun onBindViewHolder(holder: CultureHolder, position: Int) {
            val culture = cultureFilterList[position]
            holder.apply {
                holder.bind(culture, position)
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