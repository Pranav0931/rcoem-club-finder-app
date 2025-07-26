package com.hdaf.clubfinder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.hdaf.clubfinder.ui.ClubsFragment
import com.hdaf.clubfinder.ui.EventsFragment
import com.hdaf.clubfinder.ui.BlueprintFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.nav_clubs -> selectedFragment = ClubsFragment()
                R.id.nav_events -> selectedFragment = EventsFragment()
                R.id.nav_blueprint -> selectedFragment = BlueprintFragment()
            }

            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit()
            }
            true
        }

        // Set the default fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ClubsFragment())
                .commit()
            bottomNav.selectedItemId = R.id.nav_clubs
        }
    }
}
