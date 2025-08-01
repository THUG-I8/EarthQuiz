package com.earth.quiz.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.earth.quiz.data.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuScreen(
    onStartQuiz: () -> Unit,
    onViewStats: () -> Unit,
    onSettings: () -> Unit,
    onAbout: () -> Unit
) {
    var showAnimations by remember { mutableStateOf(false) }
    
    LaunchedEffect(key1 = true) {
        showAnimations = true
    }
    
    val bounce by animateFloatAsState(
        targetValue = if (showAnimations) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = EaseOutBack
        ),
        label = "bounce"
    )
    
    val fadeIn by animateFloatAsState(
        targetValue = if (showAnimations) 1f else 0f,
        animationSpec = tween(
            durationMillis = 800,
            delayMillis = 200
        ),
        label = "fadeIn"
    )
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header with animations
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.alpha(fadeIn)
            ) {
                Text(
                    text = "⚡",
                    fontSize = 60.sp,
                    modifier = Modifier.scale(bounce)
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "EarthQuiz",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                
                Text(
                    text = "اختبار معلومات الأرض 🌍",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center
                )
            }
        }
        
        // Menu buttons with staggered animations
        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
        
        item {
            MenuButton(
                text = "🚀 ابدأ الكويز",
                description = "اختر الفئة وابدأ الاختبار",
                icon = "🎯",
                onClick = onStartQuiz,
                delay = 400
            )
        }
        
        item {
            MenuButton(
                text = "📊 الإحصائيات",
                description = "شاهد نتائجك السابقة",
                icon = "📈",
                onClick = onViewStats,
                delay = 600
            )
        }
        
        item {
            MenuButton(
                text = "⚙️ الإعدادات",
                description = "تخصيص التطبيق",
                icon = "🔧",
                onClick = onSettings,
                delay = 800
            )
        }
        
        item {
            MenuButton(
                text = "ℹ️ حول التطبيق",
                description = "معلومات عن EarthQuiz",
                icon = "📖",
                onClick = onAbout,
                delay = 1000
            )
        }
        
        // Quick stats card
        item {
            Spacer(modifier = Modifier.height(24.dp))
            QuickStatsCard()
        }
    }
}

@Composable
fun MenuButton(
    text: String,
    description: String,
    icon: String,
    onClick: () -> Unit,
    delay: Int
) {
    var showButton by remember { mutableStateOf(false) }
    
    LaunchedEffect(key1 = true) {
        kotlinx.coroutines.delay(delay.toLong())
        showButton = true
    }
    
    val buttonScale by animateFloatAsState(
        targetValue = if (showButton) 1f else 0f,
        animationSpec = tween(
            durationMillis = 500,
            easing = EaseOutBack
        ),
        label = "buttonScale"
    )
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .scale(buttonScale),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClick() }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = icon,
                    fontSize = 32.sp
                )
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = text,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    
                    Text(
                        text = description,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}

@Composable
fun QuickStatsCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "📊 إحصائيات سريعة",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                QuickStatItem("🎯", "0", "اختبارات")
                QuickStatItem("⭐", "0", "نقاط")
                QuickStatItem("🏆", "0", "إنجازات")
            }
        }
    }
}

@Composable
fun QuickStatItem(icon: String, value: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = icon,
            fontSize = 24.sp
        )
        
        Text(
            text = value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        
        Text(
            text = label,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
        )
    }
}