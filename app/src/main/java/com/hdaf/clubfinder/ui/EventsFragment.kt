package com.hdaf.clubfinder.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.hdaf.clubfinder.Event
import com.hdaf.clubfinder.EventAdapter
import com.hdaf.clubfinder.R

class EventsFragment : Fragment() {

    private lateinit var db: FirebaseFirestore
    private lateinit var eventAdapter: EventAdapter
    private val eventsList = mutableListOf<Event>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_events, container, false)

        db = FirebaseFirestore.getInstance()
        val recyclerView = view.findViewById<RecyclerView>(R.id.events_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        eventAdapter = EventAdapter(eventsList)
        recyclerView.adapter = eventAdapter

        listenForEvents()

        return view
    }

    private fun listenForEvents() {
        // Listen for real-time updates from the "events" collection
        // Order by timestamp in descending order to show the newest events first
        db.collection("events")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w("EventsFragment", "Listen failed.", e)
                    return@addSnapshotListener
                }

                if (snapshots != null) {
                    eventsList.clear()
                    for (doc in snapshots) {
                        val event = doc.toObject(Event::class.java)
                        eventsList.add(event)
                    }
                    eventAdapter.updateEvents(eventsList)
                }
            }
    }
}
