package com.earth.quiz.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.earth.quiz.data.Category
import com.earth.quiz.ui.theme.QuizColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuScreen(
    onStartQuiz: () -> Unit,
    onViewStats: () -> Unit,
    onSettings: () -> Unit,
    onAbout: () -> Unit,
    onDailyChallenge: () -> Unit,
    onAchievements: () -> Unit
) {
    var showAnimations by remember { mutableStateOf(false) }
    
    LaunchedEffect(key1 = true) {
        showAnimations = true
    }
    
    val bounce by animateFloatAsState(
        targetValue = if (showAnimations) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1200,
            easing = EaseOutBack
        ),
        label = "bounce"
    )
    
    val fadeIn by animateFloatAsState(
        targetValue = if (showAnimations) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 300
        ),
        label = "fadeIn"
    )
    
    val slideIn by animateFloatAsState(
        targetValue = if (showAnimations) 0f else 100f,
        animationSpec = tween(
            durationMillis = 800,
            delayMillis = 500
        ),
        label = "slideIn"
    )
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        QuizColors.UltraDark,
                        QuizColors.DarkGray,
                        QuizColors.DeepPurple
                    )
                )
            )
    ) {
        // Animated background particles
        for (index in 0 until 15) {
            val particleOffset by animateFloatAsState(
                targetValue = if (showAnimations) 1f else 0f,
                animationSpec = tween(
                    durationMillis = 3000 + (index * 200),
                    delayMillis = index * 100
                ),
                label = "particle$index"
            )
            
            Box(
                modifier = Modifier
                    .offset(
                        x = (index * 60).dp * particleOffset,
                        y = (index * 40).dp * particleOffset
                    )
                    .size(6.dp)
                    .background(
                        color = QuizColors.NeonPurple.copy(alpha = 0.2f),
                        shape = CircleShape
                    )
            )
        }
        
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Enhanced Header with animations
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .alpha(fadeIn)
                        .offset(y = slideIn.dp)
                ) {
                    Text(
                        text = "⚡",
                        fontSize = 80.sp, // Bigger
                        modifier = Modifier
                            .scale(bounce)
                            .graphicsLayer {
                                shadowElevation = if (showAnimations) 25f else 0f
                            },
                        color = QuizColors.ElectricPurple
                    )
                    
                    Spacer(modifier = Modifier.height(20.dp))
                    
                    Text(
                        text = "EarthQuiz",
                        fontSize = 48.sp, // Bigger
                        fontWeight = FontWeight.Bold,
                        color = QuizColors.ElectricPurple,
                        modifier = Modifier.graphicsLayer {
                            shadowElevation = if (showAnimations) 20f else 0f
                        }
                    )
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    Text(
                        text = "اختبار معلومات الأرض 🌍",
                        fontSize = 18.sp, // Bigger
                        color = QuizColors.LightPurple,
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
                text = "🔥 التحدي اليومي",
                description = "اختبار سريع يومي",
                icon = "⚡",
                onClick = onDailyChallenge,
                delay = 600
            )
        }
        
        item {
            MenuButton(
                text = "🏆 الإنجازات",
                description = "عرض الإنجازات المكتسبة",
                icon = "💎",
                onClick = onAchievements,
                delay = 800
            )
        }
        
        item {
            MenuButton(
                text = "📊 الإحصائيات",
                description = "شاهد نتائجك السابقة",
                icon = "📈",
                onClick = onViewStats,
                delay = 1000
            )
        }
        
        item {
            MenuButton(
                text = "⚙️ الإعدادات",
                description = "تخصيص التطبيق",
                icon = "🔧",
                onClick = onSettings,
                delay = 1200
            )
        }
        
        item {
            MenuButton(
                text = "ℹ️ حول التطبيق",
                description = "معلومات عن EarthQuiz",
                icon = "📖",
                onClick = onAbout,
                delay = 1400
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
            durationMillis = 600,
            easing = EaseOutBack
        ),
        label = "buttonScale"
    )
    
    val glow by animateFloatAsState(
        targetValue = if (showButton) 1f else 0f,
        animationSpec = tween(
            durationMillis = 800,
            delayMillis = 200
        ),
        label = "glow"
    )
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .scale(buttonScale)
            .graphicsLayer {
                shadowElevation = if (showButton) 15f else 0f
                alpha = glow
            },
        colors = CardDefaults.cardColors(
            containerColor = QuizColors.MediumGray.copy(alpha = 0.8f)
        ),
        shape = RoundedCornerShape(20.dp) // More rounded
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            QuizColors.DeepPurple.copy(alpha = 0.3f),
                            QuizColors.PurpleAccent.copy(alpha = 0.2f)
                        )
                    )
                )
                .clickable { onClick() }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp), // More padding
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = icon,
                    fontSize = 40.sp, // Bigger icon
                    modifier = Modifier.graphicsLayer {
                        shadowElevation = if (showButton) 10f else 0f
                    }
                )
                
                Spacer(modifier = Modifier.width(20.dp)) // More spacing
                
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = text,
                        fontSize = 20.sp, // Bigger text
                        fontWeight = FontWeight.Bold,
                        color = QuizColors.ElectricPurple
                    )
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    Text(
                        text = description,
                        fontSize = 16.sp, // Bigger description
                        color = QuizColors.LightPurple
                    )
                }
            }
        }
    }
}

@Composable
fun QuickStatsCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                shadowElevation = 20f
            },
        colors = CardDefaults.cardColors(
            containerColor = QuizColors.DeepPurple.copy(alpha = 0.9f)
        ),
        shape = RoundedCornerShape(24.dp) // More rounded
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            QuizColors.DeepPurple.copy(alpha = 0.8f),
                            QuizColors.PurpleAccent.copy(alpha = 0.6f)
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier.padding(24.dp), // More padding
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "📊 إحصائيات سريعة",
                    fontSize = 22.sp, // Bigger
                    fontWeight = FontWeight.Bold,
                    color = QuizColors.ElectricPurple
                )
                
                Spacer(modifier = Modifier.height(20.dp)) // More spacing
                
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
}

@Composable
fun QuickStatItem(icon: String, value: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = icon,
            fontSize = 32.sp, // Bigger icon
            modifier = Modifier.graphicsLayer {
                shadowElevation = 8f
            }
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = value,
            fontSize = 24.sp, // Bigger value
            fontWeight = FontWeight.Bold,
            color = QuizColors.ElectricPurple
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(
            text = label,
            fontSize = 14.sp, // Bigger label
            color = QuizColors.LightPurple,
            textAlign = TextAlign.Center
        )
    }
}