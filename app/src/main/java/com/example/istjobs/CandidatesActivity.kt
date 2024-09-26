package com.example.istjobs

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class CandidatesActivity : AppCompatActivity() {
    private lateinit var firestore: FirebaseFirestore
    private lateinit var candidatesListView: ListView
    private lateinit var searchView: SearchView
    private lateinit var candidatesAdapter: ArrayAdapter<String>
    private val candidatesList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidates)

        firestore = FirebaseFirestore.getInstance()
        candidatesListView = findViewById(R.id.candidatesListView)
        searchView = findViewById(R.id.searchView)

        // Initialize the adapter
        candidatesAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, candidatesList)
        candidatesListView.adapter = candidatesAdapter

        // Fetch candidates from Firestore
        fetchCandidates()

        // Setup search filter
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                candidatesAdapter.filter.filter(newText)
                return true
            }
        })
        // Go Back Button
        val goBackButton = findViewById<Button>(R.id.goBackButton)
        goBackButton.setOnClickListener {
            // Navigate back to the AdminDashboardActivity
            startActivity(Intent(this, AdminDashboardActivity::class.java))
            finish()
        }
    }

    private fun fetchCandidates() {
        firestore.collection("users")
            .whereEqualTo("role", "user") // Assuming users with role "user" are candidates
            .get()
            .addOnSuccessListener { documents ->
                candidatesList.clear()
                for (document in documents) {
                    val name = document.getString("name") ?: "Unknown"
                    val skills = document.getString("skills") ?: "" // Assuming skills is stored in Firestore
                    candidatesList.add("$name - Skills: $skills")
                }
                candidatesAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                // Handle the error
            }
    }
}