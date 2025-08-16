package com.hdaf.clubfinder

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

// This data class defines the structure for an event object
data class Event(
    val eventName: String = "",
    val clubName: String = "",
    val date: String = "",
    val time: String = "",
    val venue: String = "",
    val description: String = "",
    @ServerTimestamp val timestamp: Date? = null // Firestore uses this to order events
)
