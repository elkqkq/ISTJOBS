package com.example.istjobs

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AdminLoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login) // Ensure this matches your layout file

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        emailField = findViewById(R.id.emailField)
        passwordField = findViewById(R.id.passwordField)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                login(email, password)
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Login successful, fetch user role
                firestore.collection("users").document(email).get().addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val role = document.getString("role")
                        if (role == "admin") {
                            // Navigate to Admin Dashboard
                            startActivity(Intent(this, AdminDashboardActivity::class.java))
                            finish() // Optional: Close the login activity
                        } else {
                            Toast.makeText(this, "You are not an admin", Toast.LENGTH_SHORT).show()
                            Log.d("AdminLogin", "Login successful, but user is not an admin.")
                        }
                    } else {
                        Toast.makeText(this, "No user found", Toast.LENGTH_SHORT).show()
                        Log.d("AdminLogin", "No user found in Firestore.")
                    }
                }.addOnFailureListener { e ->
                    Toast.makeText(this, "Failed to fetch user role: ${e.message}", Toast.LENGTH_SHORT).show()
                    Log.e("AdminLogin", "Firestore error: ", e)
                }
            } else {
                Toast.makeText(this, "Login failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                Log.e("AdminLogin", "Authentication error: ", task.exception)
            }
        }
    }
}