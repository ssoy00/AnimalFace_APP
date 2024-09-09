package com.project.animalface_app.kmsapp.retrofit

import android.app.Application
import android.content.Context
import com.project.animalface_app.kmsapp.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application(){

    private lateinit var okHttpClient: OkHttpClient
    private lateinit var retrofit_token: Retrofit
    private lateinit var apiService: INetworkService


    // http 퍼미션 허용 및, 로컬호스트 안될시 아이피로 확인 하기.
//    val BASE_URL = "http://10.100.201.41:8080"
    val BASE_URL = "http://121.175.218.185:8081"

    //add....................................
    var networkService: INetworkService


    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // 토큰 가져오기 작업
    fun initialize(context: Context) {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(sharedPreferences))
            .build()

        retrofit_token = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit_token.create(INetworkService::class.java)
    }

    fun getApiService(): INetworkService {
        return apiService
    }

    //사용법
    //
    // MyApplication 클래스의 인스턴스를 가져옵니다.
//    val myApplication = applicationContext as MyApplication
//    myApplication.initialize(this)
//    val apiService = myApplication.getApiService()

    init {
        networkService = retrofit.create(INetworkService::class.java)
    }
}