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
        targetValue = if (startAnimation) 720f else 0f,
        animationSpec = tween(
            durationMillis = 2500,
            easing = EaseInOutCubic
        ),
        label = "rotation"
    )
    
    val scale by animateFloatAsState(
        targetValue = if (startAnimation) 1.5f else 0.5f,
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
    
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(3500)
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Lightning bolt with enhanced rotation and glow
            Text(
                text = "⚡",
                fontSize = 120.sp,
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
                fontSize = 56.sp,
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
                fontSize = 20.sp,
                color = QuizColors.LightPurple,
                modifier = Modifier.alpha(alpha),
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(40.dp))
            
            // Enhanced loading indicator
            LinearProgressIndicator(
                modifier = Modifier
                    .width(250.dp)
                    .height(8.dp)
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