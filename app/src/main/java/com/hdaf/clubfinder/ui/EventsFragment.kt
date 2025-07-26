package com.hdaf.clubfinder.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hdaf.clubfinder.Event
import com.hdaf.clubfinder.EventAdapter
import com.hdaf.clubfinder.R

class EventsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_events, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.events_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = EventAdapter(getEventData())

        return view
    }

    private fun getEventData(): List<Event> {
        return listOf(
            Event("IEEE RCOEM", "Workshop on Machine Learning", "Aug 10, 2025", "11:00 AM", "Online", "An introductory workshop on the fundamentals of ML and Python libraries."),
            Event("Sanskriti Club", "Annual Cultural Fest 'Pratishruti'", "Sep 5, 2025", "6:00 PM", "College Auditorium", "A celebration of music, dance, and drama by the students of RCOEM."),
            Event("E-Cell", "Startup Pitch Competition", "Sep 15, 2025", "10:00 AM", "MBA Seminar Hall", "Pitch your startup idea to a panel of investors and industry experts."),
            Event("SAE RCOEM", "Guest Lecture on EV Technology", "Sep 22, 2025", "2:00 PM", "Mechanical Seminar Hall", "Learn about the latest trends in the Electric Vehicle industry from a TATA Motors expert.")
        )
    }
}
