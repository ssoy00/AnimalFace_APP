package com.project.animalface_app.kmsapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.animalface_app.MainActivity
import com.project.animalface_app.R
import com.project.animalface_app.databinding.ActivityLoginBinding
import com.project.animalface_app.kmsapp.repository.LoginRepository
import com.project.animalface_app.kmsapp.retrofit.INetworkService
import com.project.animalface_app.kmsapp.retrofit.MyApplication
import com.project.animalface_app.kmsapp.viewModel.LoginViewModel


class LoginActivity : AppCompatActivity() {
    private lateinit var apiService: INetworkService
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myApplication = application as MyApplication
        myApplication.initialize(this)  // Initialize the application
        apiService = myApplication.getApiService()
        sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

        // ViewModel 초기화
        loginViewModel = LoginViewModel(LoginRepository(apiService, sharedPreferences))

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnEmailSignup.setOnClickListener {
            val intent = Intent(this@LoginActivity, JoinActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnLogin.setOnClickListener {
            val memberId = binding.userid.text.toString()
            val memberPw = binding.password.text.toString()

            loginViewModel.login(memberId, memberPw)
        }

        loginViewModel.loginResult.observe(this) { loginResponse ->
            if (loginResponse != null) {
                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                Log.d("lsy", "loginResponse.memberNo : ${loginResponse.memberId}")
                saveMemberNo(loginResponse.memberId)
                Log.d("LoginActivity", "서버에서 받은 회원 번호: ${loginResponse.memberId}")

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveMemberNo(memberId: String) {
        Log.d("LoginActivity", "저장할 회원 번호: $memberId")
        val editor = sharedPreferences.edit()
        editor.putString("memberId", memberId)
        editor.apply()

        Log.d("LoginActivity", "회원 번호 저장됨: $memberId")
    }
}