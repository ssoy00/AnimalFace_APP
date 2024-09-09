package com.project.animalface_app.ohjapp.ksyAPI

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.project.animalface_app.R
import com.project.animalface_app.ksyapp.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateGameMainActivity : AppCompatActivity() {
    private lateinit var apiService: GameApiService

    private lateinit var createGameName: EditText
    private lateinit var createQuestion: EditText
    private lateinit var createAnswer: EditText
    private lateinit var createResult: EditText
    private lateinit var submitButton: Button
    private lateinit var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_game_main)

        val backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {
            onBackPressed()
        }

        // Retrofit 인스턴스와 API 서비스 생성
        apiService = RetrofitClient.instance.create(GameApiService::class.java)

        // UI 요소 초기화
        createGameName = findViewById(R.id.createGameName)
        createQuestion = findViewById(R.id.createQuestion)
        createAnswer = findViewById(R.id.createAnswer)
        createResult = findViewById(R.id.createResult)
        submitButton = findViewById(R.id.submitButton)
        resetButton = findViewById(R.id.resetButton)

        // 보내기 버튼 클릭 시 처리
        submitButton.setOnClickListener {
            val createGameNo = System.currentTimeMillis() // 자동으로 생성된 번호
            val name = createGameName.text.toString()
            val question = createQuestion.text.toString()
            val answer = createAnswer.text.toString()
            val result = createResult.text.toString()

            if (name.isNotBlank() && question.isNotBlank() && answer.isNotBlank() && result.isNotBlank()) {
                val createGame = CreateGame(createGameNo, name, question, answer, result)
                createGame(createGame)
            } else {
                // 빈 필드에 대한 경고
                Log.w("MainActivity", "모든 필드를 채워주세요.")
            }
        }

        // 초기화 버튼 클릭 시 처리
        resetButton.setOnClickListener {
            createGameName.text.clear()
            createQuestion.text.clear()
            createAnswer.text.clear()
            createResult.text.clear()
        }
    }

    private fun createGame(createGame: CreateGame) {
        apiService.createGame(createGame).enqueue(object : Callback<CreateGame> {
            override fun onResponse(call: Call<CreateGame>, response: Response<CreateGame>) {
                if (response.isSuccessful) {
                    Log.d("MainActivity", "Game created: ${response.body()}")
                } else {
                    Log.e("MainActivity", "Failed to create game: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<CreateGame>, t: Throwable) {
                Log.e("MainActivity", "API call failed", t)
            }
        })
    }
}