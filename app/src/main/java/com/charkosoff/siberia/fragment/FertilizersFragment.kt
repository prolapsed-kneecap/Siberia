package com.charkosoff.siberia.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charkosoff.siberia.R
import com.charkosoff.siberia.classes.FertilizerList
import com.charkosoff.siberia.databinding.FragmentFertilizersBinding
import com.charkosoff.siberia.databinding.FragmentFertilizersListBinding
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FertilizersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FertilizersFragment : Fragment() {

    private var fertilizersNames =
        arrayListOf("Азотные", "Фосфорные", "Калийные", "Известковые", "Хлорсодержащие")
    private var adapter: FertilizersAdapter? = null
    private var _binding: FragmentFertilizersBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFertilizersBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.fertilizersRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
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

        adapter = FertilizersAdapter(fertilizersNames)
        binding.fertilizersRecyclerView.adapter = adapter
    }

    private inner class FertilizersHolder(private val fertilizersListBinding: FragmentFertilizersListBinding) :
        RecyclerView.ViewHolder(fertilizersListBinding.root),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }


        fun bind(data: String, position: Int) {
            val selectedFertilizer = FertilizerList.Fertilizers[position]
            fertilizersListBinding.fertilizersNameTextView.text = data
            fertilizersListBinding.description.setText(selectedFertilizer.description)
            fertilizersListBinding.fertilizerRes.setImageResource(selectedFertilizer.image)


            itemView.setOnClickListener {

                Toast.makeText(context, "Удобрение использовано!\n Вы получили 7 баллов!", Toast.LENGTH_SHORT)
                    .show()

                itemView.findNavController()
                    .navigate(R.id.action_navigation_fertilizers_fragment_to_navigation_viewpager_fragment)
            }
        }

        override fun onClick(v: View) {

        }
    }

    private inner class FertilizersAdapter(var fertilizers: ArrayList<String>) :
        RecyclerView.Adapter<FertilizersHolder>(), Filterable {


        var fertilizersFilterList = ArrayList<String>()

        init {
            fertilizersFilterList = fertilizers
        }

        override fun getFilter(): Filter {
            return object : Filter() {
                override fun performFiltering(constraint: CharSequence?): FilterResults {
                    val charSearch = constraint.toString()
                    fertilizersFilterList = if (charSearch.isEmpty()) {
                        fertilizers
                    } else {
                        val resultList = ArrayList<String>()
                        for (row in fertilizers) {
                            if (row.toLowerCase(Locale.ROOT)
                                    .contains(charSearch.toLowerCase(Locale.ROOT))
                            ) {
                                resultList.add(row)
                            }
                        }
                        resultList
                    }
                    val filterResults = FilterResults()
                    filterResults.values = fertilizersFilterList
                    return filterResults
                }

                @Suppress("UNCHECKED_CAST")
                override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                    fertilizersFilterList = results?.values as ArrayList<String>
                    notifyDataSetChanged()
                }

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : FertilizersHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val fertilizersListBinding =
                FragmentFertilizersListBinding.inflate(layoutInflater, parent, false)
            return FertilizersHolder(fertilizersListBinding)
        }

        override fun getItemCount() = fertilizersFilterList.size
        override fun onBindViewHolder(holder: FertilizersHolder, position: Int) {
            val fertilizer = fertilizersFilterList[position]
            holder.apply {
                holder.bind(fertilizer, position)
            }
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FertilizersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FertilizersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}