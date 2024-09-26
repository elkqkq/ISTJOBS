package com.example.istjobs

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AdminProfileActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var nameField: EditText
    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_profile)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        nameField = findViewById(R.id.nameField)
        emailField = findViewById(R.id.emailField)
        passwordField = findViewById(R.id.passwordField)
        val updateButton = findViewById<Button>(R.id.updateButton)

        // Load admin details
        loadAdminDetails()

        updateButton.setOnClickListener {
            val name = nameField.text.toString()
            val email = emailField.text.toString()
            val password = passwordField.text.toString()

            updateProfile(name, email, password)
        }
        // Go Back Button
        val goBackButton = findViewById<Button>(R.id.goBackButton)
        goBackButton.setOnClickListener {
            // Navigate back to the AdminDashboardActivity
            startActivity(Intent(this, AdminDashboardActivity::class.java))
            finish()
        }
    }

    private fun loadAdminDetails() {
        val userId = auth.currentUser?.uid
        userId?.let {
            firestore.collection("users").document(it).get().addOnSuccessListener { document ->
                if (document.exists()) {
                    nameField.setText(document.getString("name"))
                    emailField.setText(document.getString("email"))
                } else {
                    Toast.makeText(this, "No admin details found", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun updateProfile(name: String, email: String, password: String) {
        val userId = auth.currentUser?.uid
        userId?.let {
            val updates = hashMapOf<String, Any>(
                "name" to name,
                "email" to email
            )

            // Update Firestore
            firestore.collection("users").document(it).update(updates).addOnSuccessListener {
                // Update Firebase Auth email if changed
                if (email != auth.currentUser?.email) {
                    auth.currentUser?.updateEmail(email)?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Email update failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener { e ->
                Toast.makeText(this, "Error updating profile: ${e.message}", Toast.LENGTH_SHORT).show()
            }

            // Update password if provided
            if (password.isNotEmpty()) {
                auth.currentUser?.updatePassword(password)?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Password update failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}