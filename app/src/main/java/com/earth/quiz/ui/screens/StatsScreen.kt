package com.earth.quiz.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsScreen(
    onBack: () -> Unit
) {
    var showAnimations by remember { mutableStateOf(false) }
    
    LaunchedEffect(key1 = true) {
        showAnimations = true
    }
    
    val fadeIn by animateFloatAsState(
        targetValue = if (showAnimations) 1f else 0f,
        animationSpec = tween(durationMillis = 800),
        label = "fadeIn"
    )
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .alpha(fadeIn)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Text("←", fontSize = 24.sp)
            }
            
            Spacer(modifier = Modifier.width(8.dp))
            
            Text(
                text = "📊 الإحصائيات",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Overall Stats
            item {
                StatCard(
                    title = "🎯 إحصائيات عامة",
                    items = listOf(
                        StatItem("إجمالي الاختبارات", "0", "اختبار"),
                        StatItem("إجمالي النقاط", "0", "نقطة"),
                        StatItem("أفضل نتيجة", "0%", ""),
                        StatItem("متوسط الوقت", "0", "دقيقة")
                    )
                )
            }
            
            // Recent Performance
            item {
                StatCard(
                    title = "📈 الأداء الأخير",
                    items = listOf(
                        StatItem("آخر اختبار", "لم يتم بعد", ""),
                        StatItem("النتيجة", "0/0", ""),
                        StatItem("النسبة", "0%", ""),
                        StatItem("الوقت المستغرق", "0", "دقيقة")
                    )
                )
            }
            
            // Achievements
            item {
                StatCard(
                    title = "🏆 الإنجازات",
                    items = listOf(
                        StatItem("إنجازات مكتملة", "0", "من 10"),
                        StatItem("أول اختبار", "❌", ""),
                        StatItem("نتيجة مثالية", "❌", ""),
                        StatItem("مخضرم", "❌", "")
                    )
                )
            }
            
            // Category Performance
            item {
                StatCard(
                    title = "📚 أداء الفئات",
                    items = listOf(
                        StatItem("رياضة", "0%", ""),
                        StatItem("دين", "0%", ""),
                        StatItem("معلومات عامة", "0%", ""),
                        StatItem("ترفيه", "0%", "")
                    )
                )
            }
        }
    }
}

@Composable
fun StatCard(
    title: String,
    items: List<StatItem>
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            items.forEach { item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.label,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                    )
                    
                    Text(
                        text = "${item.value}${item.unit}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                if (item != items.last()) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

data class StatItem(
    val label: String,
    val value: String,
    val unit: String
)