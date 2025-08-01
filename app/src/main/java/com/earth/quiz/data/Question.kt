package com.earth.quiz.data

data class Question(
    val id: Int,
    val type: String,        // "text" or "image"
    val category: String,    // "رياضة", "دين", "معلومات عامة", etc.
    val topic: String,       // "رياضة محلية", "رياضة عالمية", etc.
    val difficulty: String,  // "سهل", "متوسط", "صعب"
    val question: String,
    val image: String? = null,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String? = null // شرح للإجابة الصحيحة
)

data class Category(
    val id: String,
    val name: String,
    val icon: String,
    val topics: List<Topic>
)

data class Topic(
    val id: String,
    val name: String,
    val questionCount: Int
)

data class QuizSettings(
    val questionCount: Int = 10,
    val timePerQuestion: Int = 20, // seconds
    val category: String = "",
    val topic: String = "",
    val difficulty: String = "ميكس"
)
