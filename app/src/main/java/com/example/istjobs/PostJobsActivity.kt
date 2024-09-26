package com.example.istjobs

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class PostJobsActivity : AppCompatActivity() {
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_jobs)

        firestore = FirebaseFirestore.getInstance()

        val jobInfo = findViewById<EditText>(R.id.jobInfoField)
        val jobStartDate = findViewById<EditText>(R.id.jobStartDateField)
        val jobCategory = findViewById<EditText>(R.id.jobCategoryField)
        val numOfVacancies = findViewById<EditText>(R.id.numOfVacanciesField)
        val companyName = findViewById<EditText>(R.id.companyField)
        val postJobButton = findViewById<Button>(R.id.postJobButton)

        // Post Job Button click listener
        postJobButton.setOnClickListener {
            val job = hashMapOf(
                "jobInfo" to jobInfo.text.toString(),
                "jobStartDate" to jobStartDate.text.toString(),
                "jobCategory" to jobCategory.text.toString(),
                "numOfVacancies" to numOfVacancies.text.toString(),
                "company" to companyName.text.toString()
            )

            // Save job details to Firestore
            firestore.collection("jobs").add(job).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Job posted successfully", Toast.LENGTH_SHORT).show()
                    finish()  // Go back after posting job
                } else {
                    Toast.makeText(this, "Failed to post job", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Go Back Button
        val goBackButton = findViewById<Button>(R.id.goBackButton)
        goBackButton.setOnClickListener {
            // Navigate back to the MainActivity
            startActivity(Intent(this, AdminDashboardActivity::class.java))
            finish()
        }
    }
}