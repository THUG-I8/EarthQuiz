package com.earth.quiz.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBack: () -> Unit
) {
    var showAnimations by remember { mutableStateOf(false) }
    var soundEnabled by remember { mutableStateOf(true) }
    var vibrationEnabled by remember { mutableStateOf(true) }
    var darkMode by remember { mutableStateOf(false) }
    var notificationsEnabled by remember { mutableStateOf(true) }
    
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
                text = "⚙️ الإعدادات",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Sound Settings
            item {
                SettingCard(
                    title = "🔊 الصوت",
                    description = "تفعيل الأصوات في التطبيق"
                ) {
                    Switch(
                        checked = soundEnabled,
                        onCheckedChange = { soundEnabled = it }
                    )
                }
            }
            
            // Vibration Settings
            item {
                SettingCard(
                    title = "📳 الاهتزاز",
                    description = "تفعيل الاهتزاز عند الإجابة"
                ) {
                    Switch(
                        checked = vibrationEnabled,
                        onCheckedChange = { vibrationEnabled = it }
                    )
                }
            }
            
            // Dark Mode
            item {
                SettingCard(
                    title = "🌙 الوضع الليلي",
                    description = "تفعيل المظهر الداكن"
                ) {
                    Switch(
                        checked = darkMode,
                        onCheckedChange = { darkMode = it }
                    )
                }
            }
            
            // Notifications
            item {
                SettingCard(
                    title = "🔔 الإشعارات",
                    description = "تفعيل إشعارات التطبيق"
                ) {
                    Switch(
                        checked = notificationsEnabled,
                        onCheckedChange = { notificationsEnabled = it }
                    )
                }
            }
            
            // Default Settings
            item {
                SettingCard(
                    title = "⚡ الإعدادات الافتراضية",
                    description = "عدد الأسئلة والوقت الافتراضي"
                ) {
                    Column {
                        Text(
                            text = "عدد الأسئلة: 10",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "الوقت لكل سؤال: 20 ثانية",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            
            // App Info
            item {
                SettingCard(
                    title = "ℹ️ معلومات التطبيق",
                    description = "إصدار التطبيق والمطور"
                ) {
                    Column {
                        Text(
                            text = "الإصدار: 1.0.0",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "المطور: EarthQuiz Team",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SettingCard(
    title: String,
    description: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )
            }
            
            content()
        }
    }
}