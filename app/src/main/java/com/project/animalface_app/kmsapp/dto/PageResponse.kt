package com.project.animalface_app.kmsapp.dto

data class PageResponse<T>(
    val content: List<T>,
    val totalPages: Int,
    val totalElements: Int,
    val number: Int,
    val size: Int
)