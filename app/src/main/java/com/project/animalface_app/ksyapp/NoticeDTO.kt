package com.project.animalface_app.ksyapp

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class NoticeDTO(
    @SerializedName("noticeNo") val noticeNo: Long,
    @SerializedName("noticeName") val noticeName: String,
    @SerializedName("noticeContents") val noticeContents: String,
    @SerializedName("date") val date: LocalDate
)
