package com.earth.quiz.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.earth.quiz.ui.theme.QuizColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementsScreen(onBack: () -> Unit) {
    var showAnimations by remember { mutableStateOf(false) }
    
    LaunchedEffect(key1 = true) {
        showAnimations = true
    }
    
    val fadeIn by animateFloatAsState(
        targetValue = if (showAnimations) 1f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "fadeIn"
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.alpha(fadeIn)
                ) {
                    Text(
                        text = "🏆",
                        fontSize = 60.sp,
                        modifier = Modifier.scale(if (showAnimations) 1.2f else 1f)
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = "الإنجازات",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = QuizColors.ElectricPurple
                    )
                    
                    Text(
                        text = "احصل على إنجازات جديدة!",
                        fontSize = 16.sp,
                        color = QuizColors.LightPurple,
                        textAlign = TextAlign.Center
                    )
                }
            }
            
            // Achievements
            items(achievements) { achievement ->
                AchievementCard(
                    achievement = achievement,
                    showAnimations = showAnimations
                )
            }
        }
        
        // Back button
        IconButton(
            onClick = onBack,
            modifier = Modifier
                .padding(16.dp)
                .size(48.dp)
                .background(
                    color = QuizColors.DeepPurple.copy(alpha = 0.8f),
                    shape = CircleShape
                )
        ) {
            Text(
                text = "←",
                fontSize = 24.sp,
                color = QuizColors.ElectricPurple
            )
        }
    }
}

@Composable
fun AchievementCard(
    achievement: Achievement,
    showAnimations: Boolean
) {
    val cardScale by animateFloatAsState(
        targetValue = if (showAnimations) 1f else 0f,
        animationSpec = tween(
            durationMillis = 600,
            delayMillis = achievement.delay
        ),
        label = "cardScale"
    )
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .scale(cardScale)
            .graphicsLayer {
                shadowElevation = if (showAnimations) 15f else 0f
            },
        colors = CardDefaults.cardColors(
            containerColor = if (achievement.unlocked) 
                QuizColors.Success.copy(alpha = 0.2f) 
            else 
                QuizColors.MediumGray.copy(alpha = 0.8f)
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = achievement.icon,
                fontSize = 40.sp,
                modifier = Modifier.graphicsLayer {
                    shadowElevation = if (showAnimations) 10f else 0f
                }
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = achievement.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (achievement.unlocked) QuizColors.Success else QuizColors.ElectricPurple
                )
                
                Text(
                    text = achievement.description,
                    fontSize = 14.sp,
                    color = QuizColors.LightPurple
                )
                
                if (achievement.unlocked) {
                    Text(
                        text = "✅ تم الحصول عليه",
                        fontSize = 12.sp,
                        color = QuizColors.Success,
                        fontWeight = FontWeight.Bold
                    )
                } else {
                    Text(
                        text = "🔒 لم يتم الحصول عليه بعد",
                        fontSize = 12.sp,
                        color = QuizColors.Warning
                    )
                }
            }
        }
    }
}

data class Achievement(
    val title: String,
    val description: String,
    val icon: String,
    val unlocked: Boolean,
    val delay: Int
)

val achievements = listOf(
    Achievement(
        title = "مبتدئ",
        description = "أكمل أول اختبار",
        icon = "🌟",
        unlocked = true,
        delay = 100
    ),
    Achievement(
        title = "متعلم نشط",
        description = "أكمل 5 اختبارات",
        icon = "📚",
        unlocked = false,
        delay = 200
    ),
    Achievement(
        title = "خبير",
        description = "احصل على 100 نقطة",
        icon = "🎯",
        unlocked = false,
        delay = 300
    ),
    Achievement(
        title = "سريع",
        description = "أكمل اختبار في أقل من دقيقة",
        icon = "⚡",
        unlocked = false,
        delay = 400
    ),
    Achievement(
        title = "مثالي",
        description = "احصل على 100% في اختبار",
        icon = "💎",
        unlocked = false,
        delay = 500
    ),
    Achievement(
        title = "مستمر",
        description = "استخدم التطبيق لمدة أسبوع",
        icon = "🔥",
        unlocked = false,
        delay = 600
    ),
    Achievement(
        title = "متنوع",
        description = "جرب جميع فئات الأسئلة",
        icon = "🌍",
        unlocked = false,
        delay = 700
    ),
    Achievement(
        title = "أسطورة",
        description = "احصل على جميع الإنجازات",
        icon = "👑",
        unlocked = false,
        delay = 800
    )
)