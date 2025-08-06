package com.hdaf.clubfinder

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ClubDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_club_detail)

        val club = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("EXTRA_CLUB", Club::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Club>("EXTRA_CLUB")
        }

        val headerLayout: LinearLayout = findViewById(R.id.detail_header)
        val logoTextView: TextView = findViewById(R.id.detail_logo)
        val fullNameTextView: TextView = findViewById(R.id.detail_full_name)
        val descriptionTextView: TextView = findViewById(R.id.detail_description)
        val leadersTextView: TextView = findViewById(R.id.detail_leaders)
        val contactTextView: TextView = findViewById(R.id.detail_contact)

        club?.let {
            // Populate the views with data
            logoTextView.text = it.logo
            fullNameTextView.text = it.fullName
            descriptionTextView.text = it.description
            leadersTextView.text = it.leaders
            contactTextView.text = it.contact
            supportActionBar?.title = it.name

            // --- NEW: Apply the club's color ---
            try {
                val clubColor = Color.parseColor(it.colorHex)
                headerLayout.setBackgroundColor(clubColor)

                // Also change the status bar color for a more immersive feel
                // Create a slightly darker color for the status bar
                val hsv = FloatArray(3)
                Color.colorToHSV(clubColor, hsv)
                hsv[2] *= 0.8f // make it 20% darker
                window.statusBarColor = Color.HSVToColor(hsv)

            } catch (e: IllegalArgumentException) {
                // Fallback if the color string is invalid
                headerLayout.setBackgroundColor(Color.DKGRAY)
            }
            // ------------------------------------
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
