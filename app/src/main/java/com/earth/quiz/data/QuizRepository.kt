package com.earth.quiz.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object QuizRepository {
    
    fun loadQuestions(context: Context): List<Question> {
        val json = context.assets.open("questions.json").bufferedReader().use { it.readText() }
        return Gson().fromJson(json, object : TypeToken<List<Question>>() {}.type)
    }
    
    fun loadCategories(context: Context): List<Category> {
        val json = context.assets.open("categories.json").bufferedReader().use { it.readText() }
        return Gson().fromJson(json, object : TypeToken<List<Category>>() {}.type)
    }
    
    fun getFilteredQuestions(
        context: Context,
        settings: QuizSettings
    ): List<Question> {
        val allQuestions = loadQuestions(context)
        
        return allQuestions
            .filter { question ->
                (settings.category.isEmpty() || question.category == settings.category) &&
                (settings.topic.isEmpty() || question.topic == settings.topic) &&
                (settings.difficulty == "ميكس" || question.difficulty == settings.difficulty)
            }
            .shuffled()
            .take(settings.questionCount)
    }
    
    fun getQuestionsByCategory(context: Context, category: String): List<Question> {
        return loadQuestions(context).filter { it.category == category }
    }
    
    fun getQuestionsByTopic(context: Context, topic: String): List<Question> {
        return loadQuestions(context).filter { it.topic == topic }
    }
}
