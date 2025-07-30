package com.earth.quiz.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.earth.quiz.data.Question

@Composable
fun QuestionCard(question: Question, onAnswer: (Boolean) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(question.question, style = MaterialTheme.typography.headlineMedium)
        question.options.forEachIndexed { i, opt ->
            Button(onClick = { onAnswer(i == question.correctIndex) }) {
                Text(opt)
            }
        }
    }
}
