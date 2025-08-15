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
import com.hdaf.clubfinder.Announcement
import com.hdaf.clubfinder.AnnouncementAdapter
import com.hdaf.clubfinder.R

class AnnouncementsFragment : Fragment() {

    private lateinit var db: FirebaseFirestore
    private lateinit var announcementAdapter: AnnouncementAdapter
    private val announcementsList = mutableListOf<Announcement>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_announcements, container, false)

        db = FirebaseFirestore.getInstance()
        val recyclerView = view.findViewById<RecyclerView>(R.id.announcements_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        announcementAdapter = AnnouncementAdapter(announcementsList)
        recyclerView.adapter = announcementAdapter

        listenForAnnouncements()

        return view
    }

    private fun listenForAnnouncements() {
        // Listen for real-time updates from the "announcements" collection
        // Order by timestamp in descending order to show newest first
        db.collection("announcements")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w("AnnouncementsFragment", "Listen failed.", e)
                    return@addSnapshotListener
                }

                if (snapshots != null) {
                    announcementsList.clear()
                    for (doc in snapshots) {
                        val announcement = doc.toObject(Announcement::class.java)
                        announcementsList.add(announcement)
                    }
                    announcementAdapter.updateAnnouncements(announcementsList)
                }
            }
    }
}
