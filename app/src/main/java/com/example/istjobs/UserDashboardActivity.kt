package com.example.istjobs


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class UserDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_dashboard) // Ensure this layout exists

        // Find buttons by their IDs
        val searchJobsButton = findViewById<Button>(R.id.searchJobsButton)
        val notificationsButton = findViewById<Button>(R.id.notificationsButton)
        val profileButton = findViewById<Button>(R.id.profileButton)
        val logoutButton = findViewById<Button>(R.id.logoutButton)
        val goBackButton = findViewById<Button>(R.id.goBackButton) // Added go back button
        // Set click listeners to navigate to different activities
        searchJobsButton.setOnClickListener {
            startActivity(Intent(this, SearchJobsActivity::class.java))
        }

        notificationsButton.setOnClickListener {
            startActivity(Intent(this, NotificationsActivity::class.java))
        }

        profileButton.setOnClickListener {
            startActivity(Intent(this, UserProfileActivity::class.java))
        }

        logoutButton.setOnClickListener {
            // Handle logout logic
            // e.g., FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, UserLoginActivity::class.java))
            finish() // Close the current activity
        }
        // Go Back Button
        goBackButton.setOnClickListener {
            // Navigate back to the HomeActivity (MainActivity)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}