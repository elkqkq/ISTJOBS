package com.example.istjobs

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class SearchJobsActivity : AppCompatActivity() {
    private lateinit var searchJobEditText: EditText
    private lateinit var searchJobButton: Button
    private lateinit var jobsListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_jobs)

        searchJobEditText = findViewById(R.id.searchJobEditText)
        searchJobButton = findViewById(R.id.searchJobButton)
        jobsListView = findViewById(R.id.jobsListView)

        // Sample job titles for demonstration
        val sampleJobs = listOf(
            "Software Engineer",
            "Data Scientist",
            "Product Manager",
            "UI/UX Designer",
            "Web Developer"
        )

        // Set up the ListView with an adapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, sampleJobs)
        jobsListView.adapter = adapter

        // Search button click listener
        searchJobButton.setOnClickListener {
            val searchText = searchJobEditText.text.toString().trim()
            val filteredJobs = sampleJobs.filter { job ->
                job.contains(searchText, ignoreCase = true)
            }
            val filteredAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, filteredJobs)
            jobsListView.adapter = filteredAdapter
        }
    }
}