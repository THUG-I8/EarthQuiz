package com.earth.quiz.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val EarthColorScheme = darkColorScheme(
    primary = androidx.compose.ui.graphics.Color(0xFF6A1B9A),
    secondary = androidx.compose.ui.graphics.Color(0xFF9C27B0),
    background = androidx.compose.ui.graphics.Color(0xFF000000)
)

@Composable
fun EarthQuizTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = EarthColorScheme, content = content)
}
