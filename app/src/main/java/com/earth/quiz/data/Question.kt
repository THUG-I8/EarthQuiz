package com.earth.quiz.data

data class Question(
    val id: Int,
    val type: String,        // "text" or "image"
    val topic: String,
    val question: String,
    val image: String? = null,
    val options: List<String>,
    val correctIndex: Int
)
