package com.earth.quiz.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ResultScreen(score: Int, total: Int, onRestart: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("نتيجتك: $score / $total", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(24.dp))
        Button(onClick = onRestart) {
            Text("إعادة اللعب")
        }
    }
}
