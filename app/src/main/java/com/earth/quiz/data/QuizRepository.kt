package com.earth.quiz.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object QuizRepository {
    fun loadQuestions(context: Context): List<Question> {
        val json = context.assets.open("questions.json").bufferedReader().use { it.readText() }
        return Gson().fromJson(json, object : TypeToken<List<Question>>() {}.type)
    }
}
