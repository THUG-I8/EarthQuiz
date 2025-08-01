package com.earth.quiz.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashComplete: () -> Unit
) {
    var startAnimation by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (startAnimation) 360f else 0f,
        animationSpec = tween(
            durationMillis = 2000,
            easing = EaseInOutCubic
        ),
        label = "rotation"
    )
    
    val scale by animateFloatAsState(
        targetValue = if (startAnimation) 1.2f else 0.8f,
        animationSpec = tween(
            durationMillis = 1500,
            easing = EaseOutBack
        ),
        label = "scale"
    )
    
    val alpha by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 500
        ),
        label = "alpha"
    )
    
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(3000)
        onSplashComplete()
    }
    
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Lightning bolt with rotation animation
            Text(
                text = "⚡",
                fontSize = 80.sp,
                modifier = Modifier.rotate(rotation)
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // App name with scale and alpha animation
            Text(
                text = "EarthQuiz",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.alpha(alpha)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Subtitle with fade in animation
            Text(
                text = "اختبار معلومات الأرض 🌍",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.alpha(alpha)
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Loading indicator
            LinearProgressIndicator(
                modifier = Modifier
                    .width(200.dp)
                    .alpha(alpha),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}