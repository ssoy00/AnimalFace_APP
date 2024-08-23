package com.project.animalface_app.kmsapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.animalface_app.MainActivity
import com.project.animalface_app.R

class LoginActivity : AppCompatActivity() {

    private lateinit var userId: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var signupButton: Button
    private lateinit var kakaoLoginButton: ImageButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userId = findViewById(R.id.userid)
        password = findViewById(R.id.password)
        loginButton = findViewById(R.id.btn_login)
        signupButton = findViewById(R.id.btn_email_signup)
        kakaoLoginButton = findViewById(R.id.btn_kakao_login)

        loginButton.setOnClickListener {
            val inputUserId = userId.text.toString()
            val inputPassword = password.text.toString()

            if(validateLogin(inputUserId, inputPassword)) {
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                // 로그인 성공 시 다음 화면으로 이동
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "로그인 실패: 아이디 또는 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            }
        }

        signupButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


        // 카카오 로그인 버튼 클릭 이벤트
        kakaoLoginButton.setOnClickListener {
            // 카카오 로그인 기능 구현 (추후 필요)
        }

        // main이라는 ID가 없으면 이 부분을 제거하거나 적절한 뷰에 적용하세요.
        val mainView = findViewById<View>(R.id.main)
        mainView?.let {
            ViewCompat.setOnApplyWindowInsetsListener(it) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }

    private fun validateLogin(inputUserId: String, inputPassword: String): Boolean {
        // DB나 SharedPreferences에서 아이디와 비밀번호를 확인하는 로직 추가
        val storedUserId = "test@example.com"  // 예시 값
        val storedPassword = "password123"     // 예시 값

        return inputUserId == storedUserId && inputPassword == storedPassword
    }

}