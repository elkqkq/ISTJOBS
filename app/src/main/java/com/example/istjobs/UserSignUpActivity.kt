package com.example.istjobs

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserSignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_signup)

        // Ensure FirebaseApp is initialized
        if (FirebaseApp.getApps(this).isEmpty()) {
            FirebaseApp.initializeApp(this)
        }

        // Initialize FirebaseAuth and FirebaseFirestore here
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val signUpButton = findViewById<Button>(R.id.signUpButton)
        signUpButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.emailField).text.toString()
            val password = findViewById<EditText>(R.id.passwordField).text.toString()
            val name = findViewById<EditText>(R.id.nameField).text.toString()

            // Check for empty fields before signing up
            if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            signUpUser(email, password, name)
        }
    }

    private fun signUpUser(email: String, password: String, name: String) {
        // Create user with email and password
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val userId = auth.currentUser?.uid
                val user = hashMapOf(
                    "name" to name,
                    "email" to email,
                    "role" to "user"  // Set role as user
                )

                // Save user details to Firestore
                firestore.collection("users").document(userId!!).set(user).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, UserDashboardActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Failed to save user details: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
                        Log.e("Firestore", "Failed to save user details: ${it.exception?.message}")
                    }
                }
            } else {
                Toast.makeText(this, "Sign up failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                Log.e("SignUp", "Sign up failed: ${task.exception?.message}")
            }
        }
    }
}
