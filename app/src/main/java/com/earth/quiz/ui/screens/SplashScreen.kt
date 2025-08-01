package com.earth.quiz.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
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

@Composable
fun SplashScreen(
    onSplashComplete: () -> Unit
) {
    var startAnimation by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (startAnimation) 720f else 0f, // Double rotation for extra coolness
        animationSpec = tween(
            durationMillis = 2500,
            easing = EaseInOutCubic
        ),
        label = "rotation"
    )
    
    val scale by animateFloatAsState(
        targetValue = if (startAnimation) 1.5f else 0.5f, // Bigger scale for impact
        animationSpec = tween(
            durationMillis = 2000,
            easing = EaseOutBack
        ),
        label = "scale"
    )
    
    val alpha by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1200,
            delayMillis = 300
        ),
        label = "alpha"
    )
    
    val glow by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1500,
            delayMillis = 1000
        ),
        label = "glow"
    )
    
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(3500) // Longer duration for cool effect
        onSplashComplete()
    }
    
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
            ),
        contentAlignment = Alignment.Center
    ) {
        // Animated background particles
        for (index in 0 until 20) {
            val particleOffset by animateFloatAsState(
                targetValue = if (startAnimation) 1f else 0f,
                animationSpec = tween(
                    durationMillis = 2000 + (index * 100),
                    delayMillis = index * 50
                ),
                label = "particle$index"
            )
            
            Box(
                modifier = Modifier
                    .offset(
                        x = (index * 40).dp * particleOffset,
                        y = (index * 30).dp * particleOffset
                    )
                    .size(4.dp)
                    .background(
                        color = QuizColors.NeonPurple.copy(alpha = 0.3f),
                        shape = CircleShape
                    )
            )
        }
        
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Lightning bolt with enhanced rotation and glow
            Text(
                text = "⚡",
                fontSize = 120.sp, // Bigger size
                modifier = Modifier
                    .rotate(rotation)
                    .scale(scale)
                    .graphicsLayer {
                        shadowElevation = if (startAnimation) 20f else 0f
                        alpha = alpha
                    },
                color = QuizColors.ElectricPurple
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // App name with enhanced styling
            Text(
                text = "EarthQuiz",
                fontSize = 56.sp, // Bigger font
                fontWeight = FontWeight.Bold,
                color = QuizColors.ElectricPurple,
                modifier = Modifier
                    .alpha(alpha)
                    .scale(scale)
                    .graphicsLayer {
                        shadowElevation = if (startAnimation) 15f else 0f
                    }
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            // Subtitle with enhanced styling
            Text(
                text = "اختبار معلومات الأرض 🌍",
                fontSize = 20.sp, // Bigger font
                color = QuizColors.LightPurple,
                modifier = Modifier.alpha(alpha),
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(40.dp))
            
            // Enhanced loading indicator
            LinearProgressIndicator(
                modifier = Modifier
                    .width(250.dp) // Wider
                    .height(8.dp) // Thicker
                    .alpha(alpha),
                color = QuizColors.NeonPurple,
                trackColor = QuizColors.MediumGray
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Loading text
            Text(
                text = "جاري التحميل...",
                fontSize = 16.sp,
                color = QuizColors.LightPurple,
                modifier = Modifier.alpha(alpha)
            )
        }
    }
}