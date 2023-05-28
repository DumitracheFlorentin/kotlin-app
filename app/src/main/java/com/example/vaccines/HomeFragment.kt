package com.example.vaccines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var adapter: MyAdapter
    private val originalVaccinesList: ArrayList<VaccineModel> = ArrayList()
    private val filteredVaccinesList: ArrayList<VaccineModel> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        searchEditText = view.findViewById(R.id.searchEditText)

        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        originalVaccinesList.add(VaccineModel("Influenza Vaccine", R.drawable.vaccine1, "Influenza vaccine description"))
        originalVaccinesList.add(VaccineModel("Measles, Mumps, Rubella (MMR) Vaccine", R.drawable.vaccine4, "MMR vaccine description"))
        originalVaccinesList.add(VaccineModel("Polio Vaccine", R.drawable.vaccine5, "Polio vaccine description"))
        originalVaccinesList.add(VaccineModel("Tetanus, Diphtheria, Pertussis (Tdap) Vaccine", R.drawable.vaccine6, "Tdap vaccine description"))
        originalVaccinesList.add(VaccineModel("Varicella (Chickenpox) Vaccine", R.drawable.vaccine7, "Varicella vaccine description"))
        originalVaccinesList.add(VaccineModel("Pneumococcal Vaccine", R.drawable.vaccine8, "Pneumococcal vaccine description"))

        // Initialize filtered list with all vaccines
        filteredVaccinesList.addAll(originalVaccinesList)

        adapter = MyAdapter(filteredVaccinesList)
        recyclerView.adapter = adapter

        // Add a listener to the search input field
        searchEditText.doAfterTextChanged { text ->
            filterVaccines(text.toString())
        }

        return view
    }

    private fun filterVaccines(query: String) {
        filteredVaccinesList.clear()

        if (query.isEmpty()) {
            filteredVaccinesList.addAll(originalVaccinesList)
        } else {
            val filteredResults = originalVaccinesList.filter { vaccine ->
                vaccine.name.contains(query, ignoreCase = true)
            }
            filteredVaccinesList.addAll(filteredResults)
        }

        adapter.notifyDataSetChanged()

        if (filteredVaccinesList.isEmpty()) {
            Toast.makeText(requireContext(), "No results found", Toast.LENGTH_SHORT).show()
        }
    }

}

