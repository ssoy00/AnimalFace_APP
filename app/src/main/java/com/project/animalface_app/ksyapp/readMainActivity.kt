package com.project.animalface_app.ksyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.project.animalface_app.databinding.ActivityReadMainBinding

class readMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReadMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding 객체 초기화
        binding = ActivityReadMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Intent로부터 데이터 수신
        val noticeName = intent.getStringExtra("noticeName")
        val noticeContents = intent.getStringExtra("noticeContents")
        val date = intent.getStringExtra("date")

        // 수신한 데이터를 ViewBinding을 통해 EditText에 설정
        binding.noticeNameEditText.setText(noticeName)
        binding.noticeContentsEditText.setText(noticeContents)
        binding.dateEditText.setText(date)
    }
}
