package com.hdaf.clubfinder

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class ChatMessage(
    @DocumentId val id: String = "", // <-- ADD THIS LINE
    val text: String = "",
    val senderName: String = "",
    val senderId: String = "",
    @ServerTimestamp val timestamp: Date? = null
)
