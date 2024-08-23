package com.project.animalface_app.ksyapp

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.project.animalface_app.R
import com.project.animalface_app.ksyapp.Notice
import java.time.LocalDate

class noticeMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_main)

        val noticeLayout: LinearLayout = findViewById(R.id.noticeContents)

        val noticeList = getSampleData()

        for (notice in noticeList) {
            val row = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                setPadding(8, 16, 8, 16)
            }

            val noticeNoView = TextView(this).apply {
                text = notice.noticeNo.toString()
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            }

            val noticeNameView = TextView(this).apply {
                text = notice.noticeName
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 3f)
            }

            val noticeContentsView = TextView(this).apply {
                text = notice.noticeContents
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2f)
            }
            val dateView = TextView(this).apply {
                text = notice.date.toString()
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2f)
            }

            row.addView(noticeNoView)
            row.addView(noticeNameView)
            row.addView(noticeContentsView)
            row.addView(dateView)

            noticeLayout.addView(row)
        }
    }

    private fun getSampleData(): List<Notice> {
        return listOf(
            Notice(1, "공지사항 제목1", "공지사항 내용1", LocalDate.now()),
            Notice(2, "공지사항 제목2", "공지사항 내용2", LocalDate.now()),
            Notice(3, "공지사항 제목3", "공지사항 내용3", LocalDate.now())
        )
    }
}
