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
import com.hdaf.clubfinder.ClubAdapter
import com.hdaf.clubfinder.ClubDetailActivity
import com.hdaf.clubfinder.ClubRepository
import com.hdaf.clubfinder.R

class ClubsFragment : Fragment() {

    private lateinit var clubAdapter: ClubAdapter
    private val allClubs = ClubRepository.clubs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_clubs, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.clubs_recycler_view)
        val searchView = view.findViewById<SearchView>(R.id.search_view)

        // Initialize the adapter and set the click listener
        clubAdapter = ClubAdapter(allClubs) { club ->
            // This is what happens when a club is clicked
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
                return false // No action on submit
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val query = newText?.lowercase()?.trim() ?: ""
                val filteredList = allClubs.filter { club ->
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
