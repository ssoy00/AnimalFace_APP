package com.project.animalface_app.ohjapp

import android.os.Build.VERSION_CODES.N
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.animalface_app.R
import com.project.animalface_app.ohjapp.models.Notice

class ReadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)

        val notice = intent.getParcelableExtra<Notice>("notice")

        val titleTextView: TextView = findViewById(R.id.noticeTitle)
        val contentTextView: TextView = findViewById(R.id.noticeContent)
        val dateTextView: TextView = findViewById(R.id.noticeDate)

        titleTextView.text = notice?.title
        contentTextView.text = notice?.content
        dateTextView.text = notice?.date
    }
}