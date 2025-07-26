package com.hdaf.clubfinder.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hdaf.clubfinder.Club
import com.hdaf.clubfinder.ClubAdapter
import com.hdaf.clubfinder.ClubDetailActivity
import com.hdaf.clubfinder.ClubRepository
import com.hdaf.clubfinder.R

class ClubsFragment : Fragment() {

    private lateinit var clubAdapter: ClubAdapter
    // Get the single source of truth for club data from the repository
    private val allClubs = ClubRepository.clubs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_clubs, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.clubs_recycler_view)
        val searchView = view.findViewById<SearchView>(R.id.search_view)

        // The adapter now correctly accepts the list and the click listener lambda
        clubAdapter = ClubAdapter(allClubs) { club ->
            // Handle club click: create an intent and navigate to the detail activity
            val intent = Intent(requireActivity(), ClubDetailActivity::class.java).apply {
                putExtra("EXTRA_CLUB", club)
            }
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = clubAdapter

        setupSearchView(searchView)

        return view
    }

    private fun setupSearchView(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // No action needed on submit, filtering is real-time
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val query = newText?.lowercase()?.trim() ?: ""
                val filteredList = allClubs.filter { club ->
                    // Filter based on name, full name, or keywords
                    club.name.lowercase().contains(query) ||
                            club.fullName.lowercase().contains(query) ||
                            club.keywords.lowercase().contains(query)
                }
                clubAdapter.updateClubs(filteredList)
                return true
            }
        })
    }
}
