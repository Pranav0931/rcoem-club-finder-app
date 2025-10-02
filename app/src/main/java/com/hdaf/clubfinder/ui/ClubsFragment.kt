package com.hdaf.clubfinder.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.ChipGroup
import com.hdaf.clubfinder.Club
import com.hdaf.clubfinder.ClubAdapter
import com.hdaf.clubfinder.ClubDetailActivity
import com.hdaf.clubfinder.ClubRepository
import com.hdaf.clubfinder.R

class ClubsFragment : Fragment() {

    private lateinit var clubAdapter: ClubAdapter
    private val allClubs = ClubRepository.clubs
    private var currentSearchQuery = ""
    private var currentFilterCategory = "All"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_clubs, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.clubs_recycler_view)
        val searchView = view.findViewById<SearchView>(R.id.search_view)
        val chipGroup = view.findViewById<ChipGroup>(R.id.chip_group_filter)

        clubAdapter = ClubAdapter(allClubs) { club ->
            // Handle club click - navigate to detail activity
            val intent = Intent(activity, ClubDetailActivity::class.java).apply {
                putExtra("EXTRA_CLUB", club)
            }
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = clubAdapter

        setupSearchView(searchView)
        setupChipGroup(chipGroup)

        return view
    }

    private fun setupChipGroup(chipGroup: ChipGroup) {
        chipGroup.setOnCheckedChangeListener { group, checkedId ->
            currentFilterCategory = when (checkedId) {
                R.id.chip_technical -> "Technical"
                R.id.chip_cultural -> "Cultural"
                R.id.chip_social -> "Social"
                R.id.chip_spiritual -> "Spiritual"
                else -> "All"
            }
            filterList()
        }
    }

    private fun setupSearchView(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                currentSearchQuery = newText?.lowercase()?.trim() ?: ""
                filterList()
                return true
            }
        })
    }

    private fun filterList() {
        val filteredList = allClubs.filter { club ->
            // Check if it matches the search query
            val matchesSearch = club.name.lowercase().contains(currentSearchQuery) ||
                    club.keywords.lowercase().contains(currentSearchQuery)

            // Check if it matches the category filter
            val matchesCategory = currentFilterCategory == "All" || club.interest == currentFilterCategory

            matchesSearch && matchesCategory
        }
        clubAdapter.updateClubs(filteredList)
    }
}
