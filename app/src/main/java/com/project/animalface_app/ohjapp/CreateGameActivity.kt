package com.example.myapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.project.animalface_app.R

class CreateGameActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var sideBarButton: ImageButton
    private lateinit var mainButton: ImageButton
    private lateinit var myPageButton: ImageButton
    private lateinit var submitBtn: Button
    private lateinit var cancelBtn: Button
    private lateinit var closeSidebarButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_game)

        // UI 요소 초기화
        drawerLayout = findViewById(R.id.drawer_layout)
        sideBarButton = findViewById(R.id.sideBarButton)
        mainButton = findViewById(R.id.mainButton)
        myPageButton = findViewById(R.id.myPageButton)
        submitBtn = findViewById(R.id.submitBtn)
        cancelBtn = findViewById(R.id.cancelBtn)
        closeSidebarButton = findViewById(R.id.close_sidebar_button)

        // 사이드바 열기 버튼 클릭 리스너 설정
        sideBarButton.setOnClickListener {
            drawerLayout.open()  // 사이드바 열기
        }

        // 사이드바 닫기 버튼 클릭 리스너 설정
        closeSidebarButton.setOnClickListener {
            drawerLayout.close()  // 사이드바 닫기
        }

        // 홈 버튼 클릭 리스너 설정
        mainButton.setOnClickListener {
            // 홈 버튼 클릭 시 동작 추가
            Toast.makeText(this, "홈 버튼 클릭됨", Toast.LENGTH_SHORT).show()
        }

        // 마이 페이지 버튼 클릭 리스너 설정
        myPageButton.setOnClickListener {
            // 마이 페이지 버튼 클릭 시 동작 추가
            Toast.makeText(this, "마이 페이지 버튼 클릭됨", Toast.LENGTH_SHORT).show()
        }

        // 제출 버튼 클릭 리스너 설정
        submitBtn.setOnClickListener {
            if (validateForm()) {
                submitForm()
            } else {
                Toast.makeText(this, "모든 필드를 입력해 주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        // 초기화 버튼 클릭 리스너 설정
        cancelBtn.setOnClickListener {
            clearForm()
        }
    }

    // 폼 유효성 검사
    private fun validateForm(): Boolean {
        val gameName = findViewById<EditText>(R.id.createGameName).text.toString().trim()
        val question = findViewById<EditText>(R.id.createQuestion).text.toString().trim()
        val answer = findViewById<EditText>(R.id.createAnswer).text.toString().trim()
        val result = findViewById<EditText>(R.id.createResult).text.toString().trim()

        return gameName.isNotEmpty() && question.isNotEmpty() && answer.isNotEmpty() && result.isNotEmpty()
    }

    // 폼 제출 처리
    private fun submitForm() {
        val gameName = findViewById<EditText>(R.id.createGameName).text.toString()
        val question = findViewById<EditText>(R.id.createQuestion).text.toString()
        val answer = findViewById<EditText>(R.id.createAnswer).text.toString()
        val result = findViewById<EditText>(R.id.createResult).text.toString()

        // 서버로 데이터를 전송하거나 다른 액티비티로 데이터 전달
        Toast.makeText(this, "폼이 제출되었습니다.", Toast.LENGTH_SHORT).show()

        // 예를 들어, 다른 액티비티로 데이터 전송
        // val intent = Intent(this, AnotherActivity::class.java)
        // intent.putExtra("gameName", gameName)
        // intent.putExtra("question", question)
        // intent.putExtra("answer", answer)
        // intent.putExtra("result", result)
        // startActivity(intent)
    }

    // 폼 초기화
    private fun clearForm() {
        findViewById<EditText>(R.id.createGameName).setText("")
        findViewById<EditText>(R.id.createQuestion).setText("")
        findViewById<EditText>(R.id.createAnswer).setText("")
        findViewById<EditText>(R.id.createResult).setText("")
    }
}
