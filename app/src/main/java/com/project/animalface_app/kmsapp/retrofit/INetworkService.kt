package com.project.animalface_app.kmsapp.retrofit

import com.project.animalface_app.kmsapp.dto.LoginRequest
import com.project.animalface_app.kmsapp.dto.LoginResponse
import com.project.animalface_app.kmsapp.dto.UserDTO
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface INetworkService {

    @POST("/api/member")
//    fun registerUser(@Body userDTO: UserDTO): Call<Void>
    fun registerUser(@Body user: UserDTO): Call<UserDTO>

    @POST("/generateToken")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @DELETE("/api/member/{memberId}")
    suspend fun deleteUser(
        @Header("Authorization") token: String,
        @Path("memberId") memberId: String
    ): Response<Unit>
}