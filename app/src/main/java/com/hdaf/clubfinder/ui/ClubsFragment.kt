package com.hdaf.clubfinder.ui

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
import com.hdaf.clubfinder.R

class ClubsFragment : Fragment() {

    private lateinit var clubAdapter: ClubAdapter
    private val allClubs = getClubData() // Get all clubs once

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_clubs, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.clubs_recycler_view)
        val searchView = view.findViewById<SearchView>(R.id.search_view)

        clubAdapter = ClubAdapter(allClubs.toMutableList()) { club ->
            // Handle club click - navigate to detail activity
        }

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = clubAdapter

        setupSearchView(searchView)

        return view
    }

    private fun setupSearchView(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val query = newText?.lowercase()?.trim() ?: ""
                val filteredList = allClubs.filter { club ->
                    club.name.lowercase().contains(query) ||
                            club.keywords.lowercase().contains(query)
                }
                clubAdapter.updateClubs(filteredList)
                return true
            }
        })
    }

    private fun getClubData(): List<Club> {
        return listOf(
            Club(1, "IEEE RCOEM Student Branch", "💡", "Technical", "General", "The largest technical professional organization for the advancement of technology.", "Dr. P. Sonare", "Student Chair", "ieee electrical electronics computer science"),
            Club(2, "CSI RCOEM Student Branch", "💻", "Technical", "Computer Science", "Computer Society of India branch, focusing on software, research, and IT trends.", "Dr. A. Jaiswal", "Student President", "csi coding software it"),
            Club(3, "SAE RCOEM Collegiate Club", "🚗", "Technical", "Mechanical", "Society of Automotive Engineers, for students interested in mobility engineering.", "Dr. V. P. Katekar", "Team Captain", "sae automotive engineering baja supra"),
            Club(4, "ISTE RCOEM Student Chapter", "📚", "Technical", "General", "Indian Society for Technical Education, promoting quality in technical education.", "Dr. S. B. Pokle", "Student Secretary", "iste technical education"),
            Club(5, "Rotaract Club of RCOEM", "🤝", "Social", "General", "A global movement of young leaders focused on service and professional development.", "Dr. A. A. Kadu", "Student President", "rotaract social service community"),
            Club(6, "RCOEM-ACM Student Chapter", "👨‍💻", "Technical", "Computer Science", "Association for Computing Machinery, for computing professionals and students.", "Prof. G. R. Gupta", "Student Chair", "acm computing algorithms research"),
            Club(7, "Team Technocrats", "🤖", "Technical", "Mechanical", "A team dedicated to robotics, participating in competitions like Robocon.", "Dr. M. M. Gupta", "Team Lead", "robotics robocon mechanical"),
            Club(8, "Sanskriti Club", "🎭", "Cultural", "General", "The official cultural body of RCOEM, organizing fests and cultural events.", "Dr. R. A. Pande", "Student Coordinator", "cultural dance music drama"),
            Club(9, "Entrepreneurship Cell (E-Cell)", "🚀", "Social", "General", "Fostering the spirit of entrepreneurship and innovation among students.", "Dr. S. D. Pohekar", "Student CEO", "ecell startup business"),
            Club(10, "Sports Club", "⚽", "Sports", "General", "Manages all sports activities, teams, and events for the college.", "Dr. A. P. Kedar", "Sports Secretary", "sports cricket football basketball")
        )
    }
}
