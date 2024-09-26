package com.example.istjobs

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class JobsActivity : AppCompatActivity() {
    private lateinit var jobInfoField: EditText
    private lateinit var startDateField: EditText
    private lateinit var expiryDateField: EditText
    private lateinit var descriptionField: EditText
    private lateinit var categoryField: EditText
    private lateinit var vacanciesField: EditText
    private lateinit var companyField: EditText
    private lateinit var displayTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jobs)

        jobInfoField = findViewById(R.id.jobInfoField)
        startDateField = findViewById(R.id.startDateField)
        expiryDateField = findViewById(R.id.expiryDateField)
        descriptionField = findViewById(R.id.descriptionField)
        categoryField = findViewById(R.id.categoryField)
        vacanciesField = findViewById(R.id.vacanciesField)
        companyField = findViewById(R.id.companyField)
        displayTextView = findViewById(R.id.displayTextView)

        findViewById<Button>(R.id.addJobButton).setOnClickListener {
            addJob()
        }
    }

    private fun addJob() {
        val jobInfo = jobInfoField.text.toString()
        val startDate = startDateField.text.toString()
        val expiryDate = expiryDateField.text.toString()
        val description = descriptionField.text.toString()
        val category = categoryField.text.toString()
        val vacancies = vacanciesField.text.toString()
        val company = companyField.text.toString()

        // Validate fields (you can add more validation as needed)
        if (jobInfo.isEmpty() || startDate.isEmpty() || expiryDate.isEmpty() || description.isEmpty() ||
            category.isEmpty() || vacancies.isEmpty() || company.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Display the job details
        val jobDetails = """
            Job Info: $jobInfo
            Start Date: $startDate
            Expiry Date: $expiryDate
            Description: $description
            Category: $category
            Vacancies: $vacancies
            Company: $company
        """.trimIndent()

        displayTextView.text = jobDetails
        Toast.makeText(this, "Job added successfully!", Toast.LENGTH_SHORT).show()

        // Clear fields after adding the job
        clearFields()
    }

    private fun clearFields() {
        jobInfoField.text.clear()
        startDateField.text.clear()
        expiryDateField.text.clear()
        descriptionField.text.clear()
        categoryField.text.clear()
        vacanciesField.text.clear()
        companyField.text.clear()
    }
}