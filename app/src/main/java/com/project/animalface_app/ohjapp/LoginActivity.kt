package com.project.animalface_app.ohjapp

import android.widget.CheckBox
import android.widget.TextView
import com.project.animalface_app.R

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val backButton: ImageButton = findViewById(R.id.backButton) // 뒤로가기 버튼 참조
        val loginButton: Button = findViewById(R.id.loginButton)
        val kakaoLoginButton: ImageButton = findViewById(R.id.kakaoLoginButton)
        val registerButton: Button = findViewById(R.id.registerButton)
        val emailEditText: EditText = findViewById(R.id.emailEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)

        backButton.setOnClickListener {
            finish() // 현재 액티비티 종료 -> 이전 액티비티로 돌아감
        }

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "아이디와 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show()
            }
        }

        kakaoLoginButton.setOnClickListener {
            Toast.makeText(this, "카카오 로그인 클릭됨", Toast.LENGTH_SHORT).show()
        }

        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}