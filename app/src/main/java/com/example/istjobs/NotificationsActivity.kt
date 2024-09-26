package com.example.istjobs


import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class NotificationsActivity : AppCompatActivity() {
    private lateinit var notificationsListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        notificationsListView = findViewById(R.id.notificationsListView)

        // Sample notifications
        val notifications = listOf(
            "You have a new job application!",
            "Your profile has been updated.",
            "New job postings are available!",
            "You have a new message from a recruiter."
        )

        // Set up the ListView with an adapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, notifications)
        notificationsListView.adapter = adapter
    }
}