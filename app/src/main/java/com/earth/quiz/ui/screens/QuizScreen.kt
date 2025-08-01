package com.earth.quiz.ui.screens

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.earth.quiz.data.*
import com.earth.quiz.ui.theme.EarthQuizTheme
import kotlinx.coroutines.delay

enum class QuizState {
    SETUP, PLAYING, RESULTS
}

@Composable
fun QuizScreen() {
    val context = LocalContext.current
    var quizState by remember { mutableStateOf(QuizState.SETUP) }
    var quizSettings by remember { mutableStateOf(QuizSettings()) }
    var questions by remember { mutableStateOf<List<Question>>(emptyList()) }
    var currentIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var correctAnswers by remember { mutableStateOf<List<Question>>(emptyList()) }
    var wrongAnswers by remember { mutableStateOf<List<Question>>(emptyList()) }
    var totalTimeSpent by remember { mutableStateOf(0) }
    var showNextQuestion by remember { mutableStateOf(false) }

    EarthQuizTheme {
        when (quizState) {
            QuizState.SETUP -> {
                QuizSetupScreen(
                    onStartQuiz = { settings ->
                        quizSettings = settings
                        questions = QuizRepository.getFilteredQuestions(context, settings)
                        currentIndex = 0
                        score = 0
                        correctAnswers = emptyList()
                        wrongAnswers = emptyList()
                        totalTimeSpent = 0
                        quizState = QuizState.PLAYING
                    }
                )
            }
            
            QuizState.PLAYING -> {
                if (questions.isNotEmpty() && currentIndex < questions.size) {
                    val currentQuestion = questions[currentIndex]
                    
                    LaunchedEffect(showNextQuestion) {
                        if (showNextQuestion) {
                            delay(2000) // Show explanation for 2 seconds
                            if (currentIndex < questions.lastIndex) {
                                currentIndex++
                                showNextQuestion = false
                            } else {
                                quizState = QuizState.RESULTS
                            }
                        }
                    }
                    
                    QuestionCard(
                        question = currentQuestion,
                        currentIndex = currentIndex,
                        totalQuestions = questions.size,
                        timePerQuestion = quizSettings.timePerQuestion,
                        onAnswer = { isCorrect ->
                            totalTimeSpent += (quizSettings.timePerQuestion - (quizSettings.timePerQuestion - totalTimeSpent))
                            
                            if (isCorrect) {
                                score++
                                correctAnswers = correctAnswers + currentQuestion
                            } else {
                                wrongAnswers = wrongAnswers + currentQuestion
                            }
                            showNextQuestion = true
                        },
                        onTimeUp = {
                            totalTimeSpent += quizSettings.timePerQuestion
                            wrongAnswers = wrongAnswers + currentQuestion
                            showNextQuestion = true
                        }
                    )
                } else {
                    quizState = QuizState.RESULTS
                }
            }
            
            QuizState.RESULTS -> {
                val result = QuizResult(
                    score = score,
                    total = questions.size,
                    correctAnswers = correctAnswers,
                    wrongAnswers = wrongAnswers,
                    timeSpent = totalTimeSpent,
                    category = quizSettings.category.ifEmpty { "جميع الفئات" },
                    difficulty = quizSettings.difficulty
                )
                
                ResultScreen(
                    result = result,
                    onRestart = {
                        // Restart with same settings
                        questions = QuizRepository.getFilteredQuestions(context, quizSettings)
                        currentIndex = 0
                        score = 0
                        correctAnswers = emptyList()
                        wrongAnswers = emptyList()
                        totalTimeSpent = 0
                        showNextQuestion = false
                        quizState = QuizState.PLAYING
                    },
                    onBackToMenu = {
                        quizState = QuizState.SETUP
                    },
                    onShare = {
                        // TODO: Implement sharing functionality
                        val shareText = "حصلت على ${result.score}/${result.total} (${(result.score.toFloat() / result.total * 100).toInt()}%) في كويز ${result.category}! 🧠✨"
                        // Intent sharing code would go here
                    }
                )
            }
        }
    }
}
