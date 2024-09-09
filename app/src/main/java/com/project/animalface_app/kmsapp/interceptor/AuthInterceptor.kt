package com.project.animalface_app.kmsapp.interceptor

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val sharedPreferences: SharedPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // SharedPreferences에서 토큰을 가져옴
        val token = sharedPreferences.getString("jwt_token", null)

        // 토큰이 존재하면 Authorization 헤더에 추가
        val newRequest = if (token != null) {
            originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            originalRequest
        }

        return chain.proceed(newRequest)
    }
}