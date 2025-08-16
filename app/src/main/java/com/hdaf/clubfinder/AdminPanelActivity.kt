package com.hdaf.clubfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

class AdminPanelActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_panel)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // Announcement Views
        val announcementTitleEditText = findViewById<TextInputEditText>(R.id.announcement_title_edit_text)
        val announcementClubNameEditText = findViewById<TextInputEditText>(R.id.announcement_club_name_edit_text)
        val announcementDescEditText = findViewById<TextInputEditText>(R.id.announcement_description_edit_text)
        val postAnnouncementButton = findViewById<Button>(R.id.post_announcement_button)

        // Event Views
        val eventNameEditText = findViewById<TextInputEditText>(R.id.event_name_edit_text)
        val eventClubNameEditText = findViewById<TextInputEditText>(R.id.event_club_name_edit_text)
        val eventDateEditText = findViewById<TextInputEditText>(R.id.event_date_edit_text)
        val eventTimeEditText = findViewById<TextInputEditText>(R.id.event_time_edit_text)
        val eventVenueEditText = findViewById<TextInputEditText>(R.id.event_venue_edit_text)
        val postEventButton = findViewById<Button>(R.id.post_event_button)

        // Other Views
        val logoutButton = findViewById<Button>(R.id.logout_button)

        // --- Logic for Posting Announcements ---
        postAnnouncementButton.setOnClickListener {
            val title = announcementTitleEditText.text.toString().trim()
            val clubName = announcementClubNameEditText.text.toString().trim()
            val description = announcementDescEditText.text.toString().trim()

            if (title.isEmpty() || clubName.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please fill all announcement fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val announcement = hashMapOf(
                "title" to title,
                "clubName" to clubName,
                "description" to description,
                "timestamp" to Date()
            )

            db.collection("announcements")
                .add(announcement)
                .addOnSuccessListener {
                    Toast.makeText(this, "Announcement posted successfully", Toast.LENGTH_SHORT).show()
                    announcementTitleEditText.text?.clear()
                    announcementClubNameEditText.text?.clear()
                    announcementDescEditText.text?.clear()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
        }

        // --- Logic for Posting Events ---
        postEventButton.setOnClickListener {
            val eventName = eventNameEditText.text.toString().trim()
            val clubName = eventClubNameEditText.text.toString().trim()
            val date = eventDateEditText.text.toString().trim()
            val time = eventTimeEditText.text.toString().trim()
            val venue = eventVenueEditText.text.toString().trim()

            if (eventName.isEmpty() || clubName.isEmpty() || date.isEmpty() || time.isEmpty() || venue.isEmpty()) {
                Toast.makeText(this, "Please fill all event fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val event = Event(
                eventName = eventName,
                clubName = clubName,
                date = date,
                time = time,
                venue = venue,
                description = ""
            )

            db.collection("events")
                .add(event)
                .addOnSuccessListener {
                    Toast.makeText(this, "Event posted successfully", Toast.LENGTH_SHORT).show()
                    eventNameEditText.text?.clear()
                    eventClubNameEditText.text?.clear()
                    eventDateEditText.text?.clear()
                    eventTimeEditText.text?.clear()
                    eventVenueEditText.text?.clear()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
        }


        // --- Logic for Logout ---
        logoutButton.setOnClickListener {
            auth.signOut()
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
