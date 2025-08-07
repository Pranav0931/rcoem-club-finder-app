package com.hdaf.clubfinder

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hdaf.clubfinder.ui.AnnouncementsFragment
import com.hdaf.clubfinder.ui.ChatFragment
import com.hdaf.clubfinder.ui.ClubsFragment
import com.hdaf.clubfinder.ui.EventsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        if (savedInstanceState == null) {
            replaceFragment(ClubsFragment())
        }

        bottomNav.setOnItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.nav_clubs -> selectedFragment = ClubsFragment()
                R.id.nav_announcements -> selectedFragment = AnnouncementsFragment()
                R.id.nav_chat -> selectedFragment = ChatFragment()
                R.id.nav_events -> selectedFragment = EventsFragment()
            }
            if (selectedFragment != null) {
                replaceFragment(selectedFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_admin_login -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
