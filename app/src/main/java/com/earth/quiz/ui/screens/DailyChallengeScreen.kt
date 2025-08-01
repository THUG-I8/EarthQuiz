package com.earth.quiz.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyChallengeScreen(onBack: () -> Unit, onStartChallenge: () -> Unit) {
    var showAnimations by remember { mutableStateOf(false) }
    
    LaunchedEffect(key1 = true) {
        showAnimations = true
    }
    
    val fadeIn by animateFloatAsState(
        targetValue = if (showAnimations) 1f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "fadeIn"
    )
    
    val pulse by animateFloatAsState(
        targetValue = if (showAnimations) 1.1f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Header
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.alpha(fadeIn)
                ) {
                    Text(
                        text = "🔥",
                        fontSize = 80.sp,
                        modifier = Modifier.scale(pulse)
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = "التحدي اليومي",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = QuizColors.ElectricPurple
                    )
                    
                    Text(
                        text = "اختبر معلوماتك اليوم!",
                        fontSize = 18.sp,
                        color = QuizColors.LightPurple,
                        textAlign = TextAlign.Center
                    )
                }
            }
            
            // Challenge Card
            item {
                ChallengeCard(
                    showAnimations = showAnimations,
                    onStartChallenge = onStartChallenge
                )
            }
            
            // Rewards
            item {
                RewardsCard(showAnimations = showAnimations)
            }
            
            // Streak
            item {
                StreakCard(showAnimations = showAnimations)
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
fun ChallengeCard(
    showAnimations: Boolean,
    onStartChallenge: () -> Unit
) {
    val cardScale by animateFloatAsState(
        targetValue = if (showAnimations) 1f else 0f,
        animationSpec = tween(
            durationMillis = 800,
            delayMillis = 300
        ),
        label = "cardScale"
    )
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .scale(cardScale)
            .graphicsLayer {
                shadowElevation = if (showAnimations) 25f else 0f
            },
        colors = CardDefaults.cardColors(
            containerColor = QuizColors.DeepPurple.copy(alpha = 0.9f)
        ),
        shape = RoundedCornerShape(24.dp)
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
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "⚡",
                    fontSize = 60.sp,
                    modifier = Modifier.graphicsLayer {
                        shadowElevation = if (showAnimations) 15f else 0f
                    }
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "10 أسئلة سريعة",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = QuizColors.ElectricPurple
                )
                
                Text(
                    text = "اختبار سريع لمدة دقيقتين",
                    fontSize = 16.sp,
                    color = QuizColors.LightPurple,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Button(
                    onClick = onStartChallenge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = QuizColors.NeonPurple
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "ابدأ التحدي",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun RewardsCard(showAnimations: Boolean) {
    val cardScale by animateFloatAsState(
        targetValue = if (showAnimations) 1f else 0f,
        animationSpec = tween(
            durationMillis = 800,
            delayMillis = 500
        ),
        label = "rewardsScale"
    )
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .scale(cardScale)
            .graphicsLayer {
                shadowElevation = if (showAnimations) 20f else 0f
            },
        colors = CardDefaults.cardColors(
            containerColor = QuizColors.MediumGray.copy(alpha = 0.8f)
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "🎁 المكافآت",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = QuizColors.ElectricPurple
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                RewardItem("⭐", "10", "نقاط")
                RewardItem("🏆", "1", "إنجاز")
                RewardItem("🔥", "1", "شريط")
            }
        }
    }
}

@Composable
fun RewardItem(icon: String, value: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = icon,
            fontSize = 32.sp
        )
        
        Text(
            text = value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = QuizColors.ElectricPurple
        )
        
        Text(
            text = label,
            fontSize = 12.sp,
            color = QuizColors.LightPurple
        )
    }
}

@Composable
fun StreakCard(showAnimations: Boolean) {
    val cardScale by animateFloatAsState(
        targetValue = if (showAnimations) 1f else 0f,
        animationSpec = tween(
            durationMillis = 800,
            delayMillis = 700
        ),
        label = "streakScale"
    )
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .scale(cardScale)
            .graphicsLayer {
                shadowElevation = if (showAnimations) 20f else 0f
            },
        colors = CardDefaults.cardColors(
            containerColor = QuizColors.MediumGray.copy(alpha = 0.8f)
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "🔥",
                fontSize = 40.sp
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Text(
                    text = "الشريط الحالي",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = QuizColors.ElectricPurple
                )
                
                Text(
                    text = "3 أيام متتالية",
                    fontSize = 14.sp,
                    color = QuizColors.LightPurple
                )
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            Text(
                text = "3",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = QuizColors.NeonPurple
            )
        }
    }
}