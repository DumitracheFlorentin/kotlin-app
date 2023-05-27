package com.example.vaccines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var adapter: MyAdapter
    private val originalVaccinesList: ArrayList<VaccineModel> = ArrayList()
    private val filteredVaccinesList: ArrayList<VaccineModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        searchEditText = findViewById(R.id.searchEditText)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        originalVaccinesList.add(VaccineModel("COVID-19", R.drawable.vaccine1))
        originalVaccinesList.add(VaccineModel("Hepatitis B Vaccine", R.drawable.vaccine4))
        originalVaccinesList.add(VaccineModel("Dummy Text", R.drawable.vaccine5))
        originalVaccinesList.add(VaccineModel("Dummy Text", R.drawable.vaccine6))
        originalVaccinesList.add(VaccineModel("Dummy Text", R.drawable.vaccine7))
        originalVaccinesList.add(VaccineModel("Dummy Text", R.drawable.vaccine8))
        originalVaccinesList.add(VaccineModel("Dummy Text", R.drawable.vaccine9))

        // Initialize filtered list with all vaccines
        filteredVaccinesList.addAll(originalVaccinesList)

        adapter = MyAdapter(filteredVaccinesList)
        recyclerView.adapter = adapter

        // Add a listener to the search input field
        searchEditText.doAfterTextChanged { text ->
            filterVaccines(text.toString())
        }
    }

    private fun filterVaccines(query: String) {
        filteredVaccinesList.clear()

        if (query.isEmpty()) {
            filteredVaccinesList.addAll(originalVaccinesList)
        } else {
            for (vaccine in originalVaccinesList) {
                if (vaccine.name.contains(query, ignoreCase = true)) {
                    filteredVaccinesList.add(vaccine)
                }
            }
        }

        adapter.notifyDataSetChanged()

        if (filteredVaccinesList.isEmpty()) {
            Toast.makeText(this, "No results found", Toast.LENGTH_SHORT).show()
        }
    }
}