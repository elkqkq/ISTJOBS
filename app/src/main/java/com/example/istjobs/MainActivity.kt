package com.example.istjobs
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // User Sign-Up button
        val userSignUpButton = findViewById<Button>(R.id.userSignUpButton)
        userSignUpButton.setOnClickListener {
            val intent = Intent(this, UserSignUpActivity::class.java)
            startActivity(intent)
        }

        // User Login link
        val userLoginLink = findViewById<TextView>(R.id.userLoginLink)
        userLoginLink.setOnClickListener {
            val intent = Intent(this, UserLoginActivity::class.java)
            startActivity(intent)
        }

        // Admin Sign-Up button
        val adminSignUpButton = findViewById<Button>(R.id.adminSignUpButton)
        adminSignUpButton.setOnClickListener {
            val intent = Intent(this, AdminSignUpActivity::class.java)
            startActivity(intent)
        }

        // Admin Login link
        val adminLoginLink = findViewById<TextView>(R.id.adminLoginLink)
        adminLoginLink.setOnClickListener {
            val intent = Intent(this, AdminLoginActivity::class.java)
            startActivity(intent)
        }
    }
}