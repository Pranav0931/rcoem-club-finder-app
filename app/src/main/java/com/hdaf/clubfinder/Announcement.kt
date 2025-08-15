package com.hdaf.clubfinder

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Announcement(
    val title: String = "",
    val clubName: String = "",
    val description: String = "",
    @ServerTimestamp val timestamp: Date? = null // Firestore will populate this
)
