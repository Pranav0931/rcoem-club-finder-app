package com.hdaf.clubfinder

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.hdaf.clubfinder.ui.AnnouncementsFragment
import com.hdaf.clubfinder.ui.ChatFragment
import com.hdaf.clubfinder.ui.ClubsFragment
import com.hdaf.clubfinder.ui.EventsFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_admin_login -> {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            R.id.nav_theme_switch -> {
                val currentNightMode = AppCompatDelegate.getDefaultNightMode()
                if (currentNightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
            }
            R.id.nav_contact_us -> {
                // Example: Open an email client
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:contact@rcoem.edu")
                    putExtra(Intent.EXTRA_SUBJECT, "Feedback for Club Finder App")
                }
                startActivity(Intent.createChooser(intent, "Contact Us"))
            }
            R.id.nav_share -> {
                // Example: Share a link to the app
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "Check out the RCOEM Club Finder app! [App Link Here]")
                }
                startActivity(Intent.createChooser(intent, "Share App"))
            }
        }
        // Close the drawer when an item is tapped
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    // Handle the back button press when the drawer is open
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
