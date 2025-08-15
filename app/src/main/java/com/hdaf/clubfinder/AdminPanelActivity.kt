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

        val titleEditText = findViewById<TextInputEditText>(R.id.announcement_title_edit_text)
        val clubNameEditText = findViewById<TextInputEditText>(R.id.announcement_club_name_edit_text)
        val descriptionEditText = findViewById<TextInputEditText>(R.id.announcement_description_edit_text)
        val postButton = findViewById<Button>(R.id.post_announcement_button)
        val logoutButton = findViewById<Button>(R.id.logout_button)

        postButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val clubName = clubNameEditText.text.toString().trim()
            val description = descriptionEditText.text.toString().trim()

            if (title.isEmpty() || clubName.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create a data object for the announcement
            val announcement = hashMapOf(
                "title" to title,
                "clubName" to clubName,
                "description" to description,
                "timestamp" to Date() // To sort announcements by time
            )

            // Add a new document with a generated ID to the "announcements" collection
            db.collection("announcements")
                .add(announcement)
                .addOnSuccessListener {
                    Toast.makeText(this, "Announcement posted successfully", Toast.LENGTH_SHORT).show()
                    // Clear the fields after posting
                    titleEditText.text?.clear()
                    clubNameEditText.text?.clear()
                    descriptionEditText.text?.clear()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error posting announcement: ${e.message}", Toast.LENGTH_LONG).show()
                }
        }

        logoutButton.setOnClickListener {
            auth.signOut()
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
            // Go back to MainActivity and clear all previous activities
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
