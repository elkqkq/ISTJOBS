package com.example.istjobs

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AdminSignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_signup)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val signUpButton = findViewById<Button>(R.id.signUpButton)
        signUpButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.emailField).text.toString()
            val password = findViewById<EditText>(R.id.passwordField).text.toString()
            val name = findViewById<EditText>(R.id.nameField).text.toString()

            signUpAdmin(email, password, name)
        }
    }

    private fun signUpAdmin(email: String, password: String, name: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val userId = auth.currentUser?.uid
                val admin = hashMapOf(
                    "name" to name,
                    "email" to email,
                    "role" to "admin"  // Set role as admin
                )

                // Save admin details to Firestore
                firestore.collection("users").document(email).set(admin).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Admin registered successfully", Toast.LENGTH_SHORT).show()
                        // Navigate to Admin Dashboard
                        startActivity(Intent(this, AdminDashboardActivity::class.java))
                    }
                }
            } else {
                Toast.makeText(this, "Sign up failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}