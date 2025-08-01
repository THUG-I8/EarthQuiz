package com.earth.quiz.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

// Color Palette
private val Purple80 = Color(0xFFD0BCFF)
private val PurpleGrey80 = Color(0xFFCCC2DC)
private val Pink80 = Color(0xFFEFB8C8)

private val Purple40 = Color(0xFF6650a4)
private val PurpleGrey40 = Color(0xFF625b71)
private val Pink40 = Color(0xFF7D5260)

// Quiz App Colors
private val QuizPrimary = Color(0xFF6A1B9A)
private val QuizSecondary = Color(0xFF9C27B0)
private val QuizTertiary = Color(0xFF4CAF50)

private val QuizSuccess = Color(0xFF4CAF50)
private val QuizError = Color(0xFFF44336)
private val QuizWarning = Color(0xFFFF9800)
private val QuizInfo = Color(0xFF2196F3)

private val LightColorScheme = lightColorScheme(
    primary = QuizPrimary,
    onPrimary = Color.White,
    primaryContainer = Purple80,
    onPrimaryContainer = Purple40,
    secondary = QuizSecondary,
    onSecondary = Color.White,
    secondaryContainer = PurpleGrey80,
    onSecondaryContainer = PurpleGrey40,
    tertiary = QuizTertiary,
    onTertiary = Color.White,
    tertiaryContainer = Pink80,
    onTertiaryContainer = Pink40,
    error = QuizError,
    onError = Color.White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),
    background = Color(0xFFFFFBFE),
    onBackground = Color(0xFF1C1B1F),
    surface = Color(0xFFFFFBFE),
    onSurface = Color(0xFF1C1B1F),
    surfaceVariant = Color(0xFFE7E0EC),
    onSurfaceVariant = Color(0xFF49454F),
    outline = Color(0xFF79747E),
    outlineVariant = Color(0xFFCAC4D0),
    scrim = Color(0xFF000000),
    inverseSurface = Color(0xFF313033),
    inverseOnSurface = Color(0xFFF4EFF4),
    inversePrimary = Purple80,
    surfaceDim = Color(0xFFDDD8E1),
    surfaceBright = Color(0xFFFFFBFE),
    surfaceContainerLowest = Color(0xFFFFFFFF),
    surfaceContainerLow = Color(0xFFF7F2FA),
    surfaceContainer = Color(0xFFF1ECF4),
    surfaceContainerHigh = Color(0xFFECE6F0),
    surfaceContainerHighest = Color(0xFFE6E0E9)
)

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    onPrimary = Purple40,
    primaryContainer = Purple40,
    onPrimaryContainer = Purple80,
    secondary = PurpleGrey80,
    onSecondary = PurpleGrey40,
    secondaryContainer = PurpleGrey40,
    onSecondaryContainer = PurpleGrey80,
    tertiary = Pink80,
    onTertiary = Pink40,
    tertiaryContainer = Pink40,
    onTertiaryContainer = Pink80,
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
    background = Color(0xFF0F0F0F),
    onBackground = Color(0xFFE6E0E9),
    surface = Color(0xFF0F0F0F),
    onSurface = Color(0xFFE6E0E9),
    surfaceVariant = Color(0xFF49454F),
    onSurfaceVariant = Color(0xFFCAC4D0),
    outline = Color(0xFF938F99),
    outlineVariant = Color(0xFF49454F),
    scrim = Color(0xFF000000),
    inverseSurface = Color(0xFFE6E0E9),
    inverseOnSurface = Color(0xFF313033),
    inversePrimary = Purple40,
    surfaceDim = Color(0xFF0F0F0F),
    surfaceBright = Color(0xFF383538),
    surfaceContainerLowest = Color(0xFF0A0A0A),
    surfaceContainerLow = Color(0xFF1C1B1F),
    surfaceContainer = Color(0xFF201F23),
    surfaceContainerHigh = Color(0xFF2B292D),
    surfaceContainerHighest = Color(0xFF363438)
)

// Custom Typography
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
    darkTheme: Boolean = false, // You can make this dynamic based on system settings
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
    val Success = QuizSuccess
    val Error = QuizError
    val Warning = QuizWarning
    val Info = QuizInfo
    val Gold = Color(0xFFFFD700)
    val Silver = Color(0xFFC0C0C0)
    val Bronze = Color(0xFFCD7F32)
}
