package com.project.animalface_app.ohjapp.models

data class Notice(
    val number: Int,     // 공지사항 번호
    val title: String,   // 공지사항 제목
    val date: String,    // 공지사항 날짜
    val content: String  // 공지사항 내용
)
