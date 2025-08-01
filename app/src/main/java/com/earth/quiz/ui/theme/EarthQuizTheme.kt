package com.earth.quiz.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

// Modern Dark Purple & Black Color Palette
private val DeepPurple = Color(0xFF6A1B9A)
private val PurpleAccent = Color(0xFF9C27B0)
private val DarkPurple = Color(0xFF4A148C)
private val LightPurple = Color(0xFFBA68C8)
private val UltraDark = Color(0xFF0A0A0A)
private val DarkGray = Color(0xFF1A1A1A)
private val MediumGray = Color(0xFF2D2D2D)
private val LightGray = Color(0xFF424242)
private val NeonPurple = Color(0xFFE040FB)
private val ElectricPurple = Color(0xFFAA00FF)

private val LightColorScheme = lightColorScheme(
    primary = DeepPurple,
    onPrimary = Color.White,
    primaryContainer = LightPurple,
    onPrimaryContainer = DarkPurple,
    secondary = PurpleAccent,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE1BEE7),
    onSecondaryContainer = DarkPurple,
    tertiary = NeonPurple,
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFF3E5F5),
    onTertiaryContainer = DarkPurple,
    error = Color(0xFFD32F2F),
    onError = Color.White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),
    background = Color(0xFFFAFAFA),
    onBackground = UltraDark,
    surface = Color.White,
    onSurface = UltraDark,
    surfaceVariant = Color(0xFFF3E5F5),
    onSurfaceVariant = MediumGray,
    outline = LightGray,
    outlineVariant = Color(0xFFC7C7C7),
    scrim = Color(0xFF000000),
    inverseSurface = MediumGray,
    inverseOnSurface = Color.White,
    inversePrimary = LightPurple,
    surfaceTint = DeepPurple
)

private val DarkColorScheme = darkColorScheme(
    primary = ElectricPurple,
    onPrimary = Color.White,
    primaryContainer = DeepPurple,
    onPrimaryContainer = LightPurple,
    secondary = PurpleAccent,
    onSecondary = Color.White,
    secondaryContainer = DarkPurple,
    onSecondaryContainer = LightPurple,
    tertiary = NeonPurple,
    onTertiary = Color.White,
    tertiaryContainer = DarkPurple,
    onTertiaryContainer = LightPurple,
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
    background = UltraDark,
    onBackground = Color.White,
    surface = DarkGray,
    onSurface = Color.White,
    surfaceVariant = MediumGray,
    onSurfaceVariant = Color(0xFFC7C7C7),
    outline = LightGray,
    outlineVariant = MediumGray,
    scrim = Color(0xFF000000),
    inverseSurface = Color.White,
    inverseOnSurface = UltraDark,
    inversePrimary = DeepPurple,
    surfaceTint = ElectricPurple
)

// Custom Typography with modern feel
private val QuizTypography = Typography(
    displayLarge = Typography().displayLarge.copy(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold
    ),
    headlineLarge = Typography().headlineLarge.copy(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold
    ),
    titleLarge = Typography().titleLarge.copy(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold
    ),
    bodyLarge = Typography().bodyLarge.copy(
        fontFamily = FontFamily.Default
    )
)

@Composable
fun EarthQuizTheme(
    darkTheme: Boolean = true, // Default to dark theme for cool look
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = QuizTypography,
        content = content
    )
}

// Extension colors for quiz-specific states
object QuizColors {
    val Success = Color(0xFF4CAF50)
    val Error = Color(0xFFF44336)
    val Warning = Color(0xFFFF9800)
    val Info = Color(0xFF2196F3)
    val Gold = Color(0xFFFFD700)
    val Silver = Color(0xFFC0C0C0)
    val Bronze = Color(0xFFCD7F32)
    val NeonPurple = Color(0xFFE040FB)
    val ElectricPurple = Color(0xFFAA00FF)
    val DeepPurple = Color(0xFF6A1B9A)
    val UltraDark = Color(0xFF0A0A0A)
    val DarkGray = Color(0xFF1A1A1A)
    val MediumGray = Color(0xFF2D2D2D)
    val LightGray = Color(0xFF424242)
    val PurpleAccent = Color(0xFF9C27B0)
    val LightPurple = Color(0xFFBA68C8)
    val DarkPurple = Color(0xFF4A148C)
}
