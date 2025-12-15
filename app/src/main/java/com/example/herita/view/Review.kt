package com.example.herita.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.herita.data.repository.QuizReviewData
import com.example.herita.data.repository.QuizReviewItem
import com.example.herita.viewmodel.QuizUiState
import com.example.herita.viewmodel.QuizViewModel

@Composable
fun QuizReviewScreen(
    viewModel: QuizViewModel = viewModel(),
    onFinish: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        is QuizUiState.Loading -> {
            LoadingScreen()
        }
        is QuizUiState.Review -> {
            ReviewQuizContent(
                data = state.data,
                onFinish = onFinish
            )
        }
        is QuizUiState.Error -> {
            ErrorScreen(
                message = state.message,
                onRetry = { viewModel.retry() }
            )
        }
        else -> {}
    }
}

@Composable
fun ReviewQuizContent(
    data: QuizReviewData,
    onFinish: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Score Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            ScoreCard(score = data.score, maxScore = data.maxScore)
        }

        // Quiz Review List
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(data.reviews) { index, reviewItem ->
                QuizReviewCard(
                    questionNumber = index + 1,
                    reviewItem = reviewItem
                )
            }
        }

        // Selesai Button
        Button(
            onClick = onFinish,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(56.dp)
                .background(Color.White),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            ),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                text = "Selesai",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ScoreCard(score: Int, maxScore: Int) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .background(Color.Black)
            .padding(horizontal = 32.dp, vertical = 12.dp)
    ) {
        Text(
            text = "Nilai: $score/$maxScore",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun QuizReviewCard(
    questionNumber: Int,
    reviewItem: QuizReviewItem
) {
    var isExpanded by remember { mutableStateOf(false) }
    val isCorrect = reviewItem.correctOption.index == reviewItem.userAnswer.index

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header: Question Number and Status Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Soal $questionNumber",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                StatusBadge(isCorrect = isCorrect)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Question
            Text(
                text = reviewItem.question,
                fontSize = 15.sp,
                color = Color.Black,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // User Answer
            Row {
                Text(
                    text = "Jawabanmu: ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "${reviewItem.userAnswer.index}. ${reviewItem.userAnswer.content}",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Show Explanation Button
            Text(
                text = "Lihat penjelasan",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable { isExpanded = !isExpanded }
                    .padding(4.dp),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bodyMedium.copy(
                    textDecoration = androidx.compose.ui.text.style.TextDecoration.Underline
                )
            )

            // Expandable Explanation Section
            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        thickness = DividerDefaults.Thickness, color = Color.LightGray
                    )

                    // Correct Answer (only show if wrong)
                    if (!isCorrect) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        ) {
                            Text(
                                text = "Jawaban Benar: ",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF00C853)
                            )
                            Text(
                                text = "${reviewItem.correctOption.index}. ${reviewItem.correctOption.content}",
                                fontSize = 14.sp,
                                color = Color(0xFF00C853),
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    // Explanation
                    Text(
                        text = "Pembahasan:",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = reviewItem.explanation,
                        fontSize = 14.sp,
                        color = Color.DarkGray,
                        lineHeight = 20.sp
                    )
                }
            }
        }
    }
}

@Composable
fun StatusBadge(isCorrect: Boolean) {
    val backgroundColor = if (isCorrect) Color(0xFF00C853) else Color(0xFFDC143C)
    val text = if (isCorrect) "Benar" else "Salah"

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ErrorScreen(
    message: String,
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center
            )
            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFDC143C)
                )
            ) {
                Text("Coba Lagi")
            }
        }
    }
}