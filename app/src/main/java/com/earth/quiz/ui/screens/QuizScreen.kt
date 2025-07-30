package com.earth.quiz.ui.screens

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.earth.quiz.data.QuizRepository
import com.earth.quiz.ui.theme.EarthQuizTheme

@Composable
fun QuizScreen() {
    val context = LocalContext.current
    val questions = remember { QuizRepository.loadQuestions(context).shuffled().take(10) }
    var index by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var showResult by remember { mutableStateOf(false) }

    EarthQuizTheme {
        if (showResult) {
            ResultScreen(score, questions.size) { showResult = false; index = 0; score = 0 }
        } else {
            QuestionCard(
                question = questions[index],
                onAnswer = { correct ->
                    if (correct) score++
                    if (index < questions.lastIndex) index++ else showResult = true
                }
            )
        }
    }
}
