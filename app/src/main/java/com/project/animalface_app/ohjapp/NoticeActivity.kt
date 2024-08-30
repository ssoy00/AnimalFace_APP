package com.project.animalface_app.ohjapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.animalface_app.R
import com.project.animalface_app.ohjapp.adapter.NoticeAdapter
import com.project.animalface_app.ohjapp.models.Notice

class NoticeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        val noticeList = listOf(
            Notice(1, "공지사항 제목1", "2024-08-29", "공지사항 내용1"),
            Notice(2, "공지사항 제목2", "2024-08-29", "공지사항 내용2"),
            // 추가 공지사항 항목들...
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NoticeAdapter(this, noticeList)
    }
}