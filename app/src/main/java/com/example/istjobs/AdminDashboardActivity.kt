package com.example.istjobs

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class AdminDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard) // Ensure this layout exists

        // Post Jobs Button
        val postJobsButton = findViewById<Button>(R.id.jobsButton)
        postJobsButton.setOnClickListener {
            startActivity(Intent(this, PostJobsActivity::class.java))
        }

        // Candidates Button
        val candidatesButton = findViewById<Button>(R.id.candidatesButton)
        candidatesButton.setOnClickListener {
            startActivity(Intent(this, CandidatesActivity::class.java))
        }

        // Profile Button
        val profileButton = findViewById<Button>(R.id.profileButton)
        profileButton.setOnClickListener {
            startActivity(Intent(this, AdminProfileActivity::class.java))
        }

        // Logout Button
        val logoutButton = findViewById<Button>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            // Handle Logout (e.g., sign out from Firebase)
            startActivity(Intent(this, AdminLoginActivity::class.java))
            finish()
        }

        // Go Back Button
        val goBackButton = findViewById<Button>(R.id.goBackButton)
        goBackButton.setOnClickListener {
            // Navigate back to the HomeActivity
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}