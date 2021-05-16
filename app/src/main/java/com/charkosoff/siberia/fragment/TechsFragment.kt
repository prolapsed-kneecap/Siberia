package com.charkosoff.siberia.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charkosoff.siberia.Event.EventMaster
import com.charkosoff.siberia.R
import com.charkosoff.siberia.classes.TechnicsList
import com.charkosoff.siberia.data.Data
import com.charkosoff.siberia.databinding.FragmentTechsBinding
import com.charkosoff.siberia.databinding.FragmentTechsListBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.collections.ArrayList

/**
 * A fragment representing a list of Items.
 */
class TechsFragment : Fragment() {

    private var columnCount = 1
    private var techNames =
        arrayListOf("СЗП-3,6", "СЗУ-Т-3.6", "СЗУ-3,6-04", "Енисей–1200–1НМ", "Дон 1500")
    private var adapter: TechAdapter? = null
    private var _binding: FragmentTechsBinding? = null
    private val binding get() = _binding!!
    private var eventMaster = EventMaster()

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
        _binding = FragmentTechsBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.techRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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

        adapter = TechAdapter(techNames)
        binding.techRecyclerView.adapter = adapter
    }

    private inner class TechHolder(private val techsListBinding: FragmentTechsListBinding) :
        RecyclerView.ViewHolder(techsListBinding.root),
        View.OnClickListener {


        init {
            itemView.setOnClickListener(this)
        }


        @SuppressLint("SetTextI18n")
        fun bind(data: String, position: Int) {
            techsListBinding.techNameTextView.text = data
            val selectedTech = TechnicsList.technics[position]


            techsListBinding.description.setText(selectedTech.description)
            techsListBinding.family.text = "Вид: " + selectedTech.family
            techsListBinding.techRes.setImageResource(selectedTech.image)
            itemView.setOnClickListener {

                //if(eventMaster.isTechChoiceRight(Data.currentEvent, TechnicsList.technics[position]))
                val view = View.inflate(context, R.layout.dialog_techs, null)
                val seekbar = view.findViewById<SeekBar>(R.id.seekBar)
                seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        seekBar: SeekBar?,
                        progress: Int,
                        fromUser: Boolean
                    ) {

                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {

                    }
                })

                val btn_accept = view.findViewById<Button>(R.id.btn_dialog_accept)

                val builder = MaterialAlertDialogBuilder(
                    requireContext(),
                    R.style.MaterialAlertDialog_Rounded
                )
                builder.setView(view)
                val dialog = builder.create()
                dialog.show()
                dialog.setCancelable(false)

                btn_accept.setOnClickListener() {
                    dialog.dismiss()
                    Data.currentTech = selectedTech
                    itemView.findNavController().navigate(
                        R.id.action_navigation_tech_fragment_to_navigation_main_fragment,
                        arguments
                    )
                }
            }

        }

        override fun onClick(v: View) {

        }


    }

    private inner class TechAdapter(var techs: ArrayList<String>) :
        RecyclerView.Adapter<TechHolder>(),
        Filterable {

        var techsFilterList = ArrayList<String>()

        init {
            techsFilterList = techs
        }

        override fun getFilter(): Filter {
            return object : Filter() {
                override fun performFiltering(constraint: CharSequence?): FilterResults {
                    val charSearch = constraint.toString()
                    if (charSearch.isEmpty()) {
                        techsFilterList = techs
                    } else {
                        val resultList = ArrayList<String>()
                        for (row in techs) {
                            if (row.toLowerCase(Locale.ROOT)
                                    .contains(charSearch.toLowerCase(Locale.ROOT))
                            ) {
                                resultList.add(row)
                            }
                        }
                        techsFilterList = resultList
                    }
                    val filterResults = FilterResults()
                    filterResults.values = techsFilterList
                    return filterResults
                }

                @Suppress("UNCHECKED_CAST")
                override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                    techsFilterList = results?.values as ArrayList<String>
                    notifyDataSetChanged()
                }

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : TechHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val techsListBinding = FragmentTechsListBinding.inflate(layoutInflater, parent, false)
            return TechHolder(techsListBinding)
        }

        override fun getItemCount() = techsFilterList.size
        override fun onBindViewHolder(holder: TechHolder, position: Int) {
            val tech = techsFilterList[position]
            holder.apply {
                holder.bind(tech, position)
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