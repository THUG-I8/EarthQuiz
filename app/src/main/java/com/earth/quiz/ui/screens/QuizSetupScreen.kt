package com.earth.quiz.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.earth.quiz.data.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizSetupScreen(
    onStartQuiz: (QuizSettings) -> Unit
) {
    val context = LocalContext.current
    val categories = remember { QuizRepository.loadCategories(context) }
    
    var selectedCategory by remember { mutableStateOf("") }
    var selectedTopic by remember { mutableStateOf("") }
    var questionCount by remember { mutableStateOf(10) }
    var timePerQuestion by remember { mutableStateOf(20) }
    var difficulty by remember { mutableStateOf("ميكس") }
    
    val selectedCategoryData = categories.find { it.id == selectedCategory }
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        item {
            Text(
                text = "🧠 إعداد الكويز",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
        }
        
        // Categories
        item {
            Text(
                text = "اختر الفئة:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    FilterChip(
                        onClick = { 
                            selectedCategory = if (selectedCategory == category.id) "" else category.id
                            selectedTopic = ""
                        },
                        label = { Text("${category.icon} ${category.name}") },
                        selected = selectedCategory == category.id
                    )
                }
            }
        }
        
        // Topics
        if (selectedCategoryData != null) {
            item {
                Text(
                    text = "اختر الموضوع:",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(selectedCategoryData.topics) { topic ->
                        FilterChip(
                            onClick = { 
                                selectedTopic = if (selectedTopic == topic.id) "" else topic.id
                            },
                            label = { Text("${topic.name} (${topic.questionCount})") },
                            selected = selectedTopic == topic.id
                        )
                    }
                }
            }
        }
        
        // Question Count
        item {
            Text(
                text = "عدد الأسئلة: $questionCount",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Slider(
                value = questionCount.toFloat(),
                onValueChange = { questionCount = it.toInt() },
                valueRange = 5f..25f,
                steps = 19
            )
        }
        
        // Timer Settings
        item {
            Text(
                text = "الوقت لكل سؤال: $timePerQuestion ثانية",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val timeOptions = listOf(10, 15, 20, 25, 30, 45, 60)
                items(timeOptions) { time ->
                    FilterChip(
                        onClick = { timePerQuestion = time },
                        label = { Text("${time}ث") },
                        selected = timePerQuestion == time
                    )
                }
            }
        }
        
        // Difficulty
        item {
            Text(
                text = "مستوى الصعوبة:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val difficulties = listOf("سهل", "متوسط", "صعب", "ميكس")
                items(difficulties) { diff ->
                    FilterChip(
                        onClick = { difficulty = diff },
                        label = { Text(diff) },
                        selected = difficulty == diff
                    )
                }
            }
        }
        
        // Start Button
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    onStartQuiz(
                        QuizSettings(
                            questionCount = questionCount,
                            timePerQuestion = timePerQuestion,
                            category = selectedCategory,
                            topic = selectedTopic,
                            difficulty = difficulty
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("🚀 ابدأ الكويز", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}