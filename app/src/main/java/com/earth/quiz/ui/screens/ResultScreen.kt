package com.earth.quiz.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.earth.quiz.data.Question

data class QuizResult(
    val score: Int,
    val total: Int,
    val correctAnswers: List<Question>,
    val wrongAnswers: List<Question>,
    val timeSpent: Int,
    val category: String,
    val difficulty: String
)

@Composable
fun ResultScreen(
    result: QuizResult,
    onRestart: () -> Unit,
    onBackToMenu: () -> Unit,
    onShare: () -> Unit
) {
    val percentage = (result.score.toFloat() / result.total * 100).toInt()
    val grade = when {
        percentage >= 90 -> "ممتاز" to "🏆"
        percentage >= 80 -> "جيد جداً" to "🥇"
        percentage >= 70 -> "جيد" to "🥈"
        percentage >= 60 -> "مقبول" to "🥉"
        else -> "يحتاج تحسين" to "📚"
    }
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        item {
            Text(
                text = "🎯 نتائج الكويز",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
        }
        
        // Score Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = when {
                        percentage >= 80 -> Color.Green.copy(alpha = 0.1f)
                        percentage >= 60 -> Color.Yellow.copy(alpha = 0.1f)
                        else -> Color.Red.copy(alpha = 0.1f)
                    }
                )
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = grade.second,
                        style = MaterialTheme.typography.displayLarge
                    )
                    Text(
                        text = "${result.score} / ${result.total}",
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "$percentage%",
                        style = MaterialTheme.typography.headlineMedium,
                        color = when {
                            percentage >= 80 -> Color.Green
                            percentage >= 60 -> MaterialTheme.colorScheme.primary
                            else -> Color.Red
                        }
                    )
                    Text(
                        text = grade.first,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        
        // Statistics
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatCard("✅ صحيح", result.score.toString(), Color.Green)
                StatCard("❌ خطأ", (result.total - result.score).toString(), Color.Red)
                StatCard("⏱️ الوقت", "${result.timeSpent}ث", MaterialTheme.colorScheme.primary)
            }
        }
        
        // Quiz Info
        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "📊 تفاصيل الكويز",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("الفئة: ${result.category}")
                    Text("المستوى: ${result.difficulty}")
                    Text("معدل الوقت: ${result.timeSpent / result.total}ث/سؤال")
                }
            }
        }
        
        // Achievement Badge
        if (percentage >= 90) {
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.Gold.copy(alpha = 0.2f))
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "🏆 إنجاز جديد!",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text("حصلت على درجة ممتازة!")
                    }
                }
            }
        }
        
        // Action Buttons
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onBackToMenu,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("🏠 القائمة الرئيسية")
                }
                Button(
                    onClick = onRestart,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("🔄 إعادة اللعب")
                }
            }
        }
        
        // Share Button
        item {
            OutlinedButton(
                onClick = onShare,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Share, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("📤 مشاركة النتيجة")
            }
        }
        
        // Review Section
        if (result.wrongAnswers.isNotEmpty()) {
            item {
                Text(
                    text = "📝 مراجعة الأخطاء",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            
            items(result.wrongAnswers) { question ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.Red.copy(alpha = 0.1f))
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = question.question,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "الإجابة الصحيحة: ${question.options[question.correctIndex]}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Green
                        )
                        if (question.explanation != null) {
                            Text(
                                text = question.explanation,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun StatCard(
    title: String,
    value: String,
    color: Color
) {
    Card {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = color
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

// Helper color
private val Color.Companion.Gold: Color
    get() = Color(0xFFFFD700)
