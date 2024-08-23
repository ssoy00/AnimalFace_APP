package com.project.animalface_app.ksyapp

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun fetchNotices() {
    val apiService = RetrofitClient.retrofitInstance.create(NoticeService::class.java)
    val call = apiService.getNotices()

    call.enqueue(object : Callback<List<NoticeDTO>> {
        override fun onResponse(call: Call<List<NoticeDTO>>, response: Response<List<NoticeDTO>>) {
            if (response.isSuccessful) {
                val notices = response.body()
                Log.d("API Response", "성공! ${response.body()}")
            } else {
                Log.e("API Response", "실패 ${response.errorBody()?.string()}")

            }
        }

        override fun onFailure(call: Call<List<NoticeDTO>>, t: Throwable) {
            Log.e("API Error", "실패실패 ${t.message}")
        }
    })
}