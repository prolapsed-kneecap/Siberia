package com.charkosoff.siberia.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charkosoff.siberia.R

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

    private var fertilizersNames = arrayOf("Удобрение", "Удобрение", "Удобрение", "Удобрение", "Удобрение")
    private lateinit var fertilizersRecyclerView: RecyclerView
    private var adapter: FertilizersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fertilizers, container, false)

        fertilizersRecyclerView =
            view.findViewById(R.id.fertilizers_recycler_view) as RecyclerView
        fertilizersRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()
        return view
    }


    private fun updateUI() {

        adapter = FertilizersAdapter(fertilizersNames)
        fertilizersRecyclerView.adapter = adapter
    }

    private inner class FertilizersHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        var fertilizersNameTextView: TextView = itemView.findViewById(R.id.fertilizersNameTextView)
        var fertilizersCardView: CardView = itemView.findViewById(R.id.fertilizersCardView)

        init {
            itemView.setOnClickListener(this)
        }


        fun bind(data: String) {
            fertilizersNameTextView.text = data
            fertilizersCardView.setOnClickListener {



                itemView.findNavController()
                    .navigate(R.id.action_navigation_fertilizers_fragment_to_navigation_viewpager_fragment)
            }
        }

        override fun onClick(v: View) {

        }
    }

    private inner class FertilizersAdapter(var fertilizers: Array<String>) :
        RecyclerView.Adapter<FertilizersHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : FertilizersHolder {
            val view = layoutInflater.inflate(R.layout.fragment_fertilizers_list, parent, false)
            return FertilizersHolder(view)
        }

        override fun getItemCount() = fertilizers.size
        override fun onBindViewHolder(holder: FertilizersHolder, position: Int) {
            val culture = fertilizers[position]
            holder.apply {
                holder.bind(culture)
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