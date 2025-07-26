package com.hdaf.clubfinder

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ClubDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_club_detail)

        // Retrieve the club object passed from the previous screen
        val club = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("EXTRA_CLUB", Club::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Club>("EXTRA_CLUB")
        }

        // Find the views in the layout
        val logoTextView: TextView = findViewById(R.id.detail_logo)
        val fullNameTextView: TextView = findViewById(R.id.detail_full_name)
        val descriptionTextView: TextView = findViewById(R.id.detail_description)
        val leadersTextView: TextView = findViewById(R.id.detail_leaders)
        val contactTextView: TextView = findViewById(R.id.detail_contact)

        // Populate the views with the club's data
        club?.let {
            logoTextView.text = it.logo
            fullNameTextView.text = it.fullName
            descriptionTextView.text = it.description
            leadersTextView.text = it.leaders
            contactTextView.text = it.contact

            // Set the title of the activity
            supportActionBar?.title = it.name
        }

        // Enable the back button in the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // Handle the back button press
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
