package com.earth.quiz.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.earth.quiz.data.Question
import kotlinx.coroutines.delay

@Composable
fun QuestionCard(
    question: Question,
    currentIndex: Int,
    totalQuestions: Int,
    timePerQuestion: Int,
    onAnswer: (Boolean) -> Unit,
    onTimeUp: () -> Unit
) {
    var timeLeft by remember { mutableStateOf(timePerQuestion) }
    var selectedOption by remember { mutableStateOf(-1) }
    var showAnswer by remember { mutableStateOf(false) }
    
    // Timer effect
    LaunchedEffect(question.id) {
        timeLeft = timePerQuestion
        selectedOption = -1
        showAnswer = false
        
        while (timeLeft > 0 && !showAnswer) {
            delay(1000)
            timeLeft--
        }
        if (!showAnswer) {
            onTimeUp()
        }
    }
    
    val progress = (timePerQuestion - timeLeft).toFloat() / timePerQuestion
    val timerColor = when {
        timeLeft > timePerQuestion * 0.5 -> Color.Green
        timeLeft > timePerQuestion * 0.2 -> Color.Yellow
        else -> Color.Red
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Progress and Timer
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "السؤال ${currentIndex + 1} من $totalQuestions",
                style = MaterialTheme.typography.titleMedium
            )
            Card(
                colors = CardDefaults.cardColors(containerColor = timerColor)
            ) {
                Text(
                    text = "${timeLeft}ث",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth(),
            color = timerColor
        )
        
        // Category and Topic
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                onClick = { },
                label = { Text(question.category) },
                selected = false
            )
            FilterChip(
                onClick = { },
                label = { Text(question.topic) },
                selected = false
            )
            FilterChip(
                onClick = { },
                label = { Text(question.difficulty) },
                selected = false
            )
        }
        
        // Question
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = question.question,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                
                // Image placeholder (if question has image)
                if (question.image != null) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "🖼️ صورة: ${question.image}",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
        }
        
        // Options
        question.options.forEachIndexed { index, option ->
            Button(
                onClick = {
                    if (!showAnswer) {
                        selectedOption = index
                        showAnswer = true
                        onAnswer(index == question.correctIndex)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = when {
                        !showAnswer -> MaterialTheme.colorScheme.primary
                        index == question.correctIndex -> Color.Green
                        index == selectedOption && index != question.correctIndex -> Color.Red
                        else -> MaterialTheme.colorScheme.surfaceVariant
                    }
                )
            ) {
                Text(
                    text = option,
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        
        // Explanation (shown after answer)
        if (showAnswer && question.explanation != null) {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "💡 شرح الإجابة:",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = question.explanation,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
