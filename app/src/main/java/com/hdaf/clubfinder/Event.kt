package com.hdaf.clubfinder

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

// The empty constructor is required for Firestore's automatic data mapping
data class Event(
    val eventName: String = "",
    val clubName: String = "",
    val date: String = "",
    val time: String = "",
    val venue: String = "",
    val description: String = "",
    val eventType: String = "Activity", // New field: "Activity", "Interview", "Announcement"
    @ServerTimestamp val timestamp: Date? = null
)
