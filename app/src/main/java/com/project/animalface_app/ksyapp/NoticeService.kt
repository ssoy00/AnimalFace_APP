package com.project.animalface_app.ksyapp

import retrofit2.Call
import retrofit2.http.*

interface NoticeService {


    @GET("notice/list")
    fun getNotices(): Call<List<NoticeDTO>>

    @GET("notice/read")
    fun getNotice(@Query("noticeNo") noticeNo: Long): Call<NoticeDTO>

    @POST("notice")
    fun createNotice(@Body noticeDTO: NoticeDTO): Call<NoticeDTO>

    @PUT("notice/{noticeNo}")
    fun updateNotice(
        @Path("noticeNo") noticeNo: Long,
        @Body noticeDTO: NoticeDTO
    ): Call<NoticeDTO>

    @DELETE("notice/{noticeNo}")
    fun deleteNotice(@Path("noticeNo") noticeNo: Long): Call<Void>

}