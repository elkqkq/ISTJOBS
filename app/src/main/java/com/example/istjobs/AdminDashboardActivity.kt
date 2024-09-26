package com.example.istjobs

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class AdminDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard) // Ensure this layout exists

        findViewById<ImageButton>(R.id.jobsButton).setOnClickListener {
            // Navigate to Jobs Activity
            startActivity(Intent(this, JobsActivity::class.java)) // Ensure this activity exists
        }

        findViewById<ImageButton>(R.id.candidatesButton).setOnClickListener {
            // Navigate to Candidates Activity
            startActivity(Intent(this, CandidatesActivity::class.java)) // Ensure this activity exists
        }

        findViewById<ImageButton>(R.id.profileButton).setOnClickListener {
            // Navigate to Admin Profile Activity
            startActivity(Intent(this, AdminProfileActivity::class.java)) // Ensure this activity exists
        }

        findViewById<ImageButton>(R.id.logoutButton).setOnClickListener {
            // Handle logout
            FirebaseAuth.getInstance().signOut()
            // Navigate to Login Activity
            startActivity(Intent(this, AdminLoginActivity::class.java))
            finish()
        }
    }
}