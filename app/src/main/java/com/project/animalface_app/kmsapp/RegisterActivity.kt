package com.project.animalface_app.kmsapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.animalface_app.R

class RegisterActivity : AppCompatActivity() {

    private lateinit var userId: EditText
    private lateinit var password: EditText
    private lateinit var password2: EditText
    private lateinit var nickname: EditText
    private lateinit var completeButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        userId = findViewById(R.id.userid)
        password = findViewById(R.id.password)
        password2 = findViewById(R.id.password2)
        nickname = findViewById(R.id.nickname)
        completeButton = findViewById(R.id.btn_complete)

        // 가입 완료 버튼 클릭 이벤트
        completeButton.setOnClickListener {
            val inputUserId = userId.text.toString()
            val inputPassword = password.text.toString()
            val inputPassword2 = password2.text.toString()
            val inputNickname = nickname.text.toString()

            if (validateSignup(inputUserId, inputPassword, inputPassword2, inputNickname)) {
                // DB에 회원정보 저장 (추후 구현)
                Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                finish()  // 회원가입 완료 후 화면 닫기
            }

            // main이라는 ID가 없으면 이 부분을 제거하거나 적절한 뷰에 적용하세요.
            val mainView = findViewById<View>(R.id.main)
            mainView?.let {
                ViewCompat.setOnApplyWindowInsetsListener(it) { v, insets ->
                    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                    v.setPadding(
                        systemBars.left,
                        systemBars.top,
                        systemBars.right,
                        systemBars.bottom
                    )
                    insets
                }
            }
        }
    }
    // 회원가입 유효성 검사 함수
    private fun validateSignup(userId: String, password: String, password2: String, nickname: String): Boolean {
        if (userId.isEmpty() || password.isEmpty() || password2.isEmpty() || nickname.isEmpty()) {
            Toast.makeText(this, "모든 필드를 입력해주세요", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password != password2) {
            Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
            return false
        }

        // 이메일 유효성 검사 (간단한 형식 검사)
        if (userId.length < 4) {
            Toast.makeText(this, "아이디는 4자 이상이어야 합니다", Toast.LENGTH_SHORT).show()
            return false
        }

        // 추가적인 유효성 검사 필요 시 여기서 구현

        return true
    }
}