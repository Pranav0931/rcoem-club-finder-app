package com.hdaf.clubfinder

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

// The empty constructor is required for Firestore's automatic data mapping
data class Announcement(
    val title: String = "",
    val clubName: String = "",
    val description: String = "",
    val date: String = "", // New field: To place it on the calendar
    val eventType: String = "Announcement", // New field: "Activity", "Interview", "Announcement"
    @ServerTimestamp val timestamp: Date? = null
)
