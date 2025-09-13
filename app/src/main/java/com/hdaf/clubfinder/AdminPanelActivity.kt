package com.hdaf.clubfinder

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class AdminPanelActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_panel)

        db = FirebaseFirestore.getInstance()

        val postEventButton = findViewById<Button>(R.id.post_event_button)

        postEventButton.setOnClickListener {
            postCalendarEntry()
        }
    }

    private fun postCalendarEntry() {
        val titleInput = findViewById<EditText>(R.id.event_title_input)
        val clubNameInput = findViewById<EditText>(R.id.event_club_name_input)
        val dateInput = findViewById<EditText>(R.id.event_date_input)
        val timeInput = findViewById<EditText>(R.id.event_time_input)
        val venueInput = findViewById<EditText>(R.id.event_venue_input)
        val descriptionInput = findViewById<EditText>(R.id.event_description_input)
        val eventTypeGroup = findViewById<RadioGroup>(R.id.event_type_group)

        val title = titleInput.text.toString().trim()
        val clubName = clubNameInput.text.toString().trim()
        val date = dateInput.text.toString().trim()
        val time = timeInput.text.toString().trim()
        val venue = venueInput.text.toString().trim()
        val description = descriptionInput.text.toString().trim()

        if (title.isEmpty() || clubName.isEmpty() || date.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedTypeId = eventTypeGroup.checkedRadioButtonId
        val eventType = when (selectedTypeId) {
            R.id.radio_interview -> "Interview"
            R.id.radio_announcement -> "Announcement"
            else -> "Activity"
        }

        // We will now use the more generic 'Event' data class for all calendar entries
        val event = Event(
            eventName = title,
            clubName = clubName,
            date = date,
            time = time,
            venue = venue,
            description = description,
            eventType = eventType
        )

        // We will save all entries to a single "events" collection for simplicity
        db.collection("events")
            .add(event)
            .addOnSuccessListener {
                Toast.makeText(this, "Entry posted to calendar successfully", Toast.LENGTH_SHORT).show()
                // Clear the fields after successful post
                titleInput.text.clear()
                clubNameInput.text.clear()
                dateInput.text.clear()
                timeInput.text.clear()
                venueInput.text.clear()
                descriptionInput.text.clear()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error posting entry: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }
}
