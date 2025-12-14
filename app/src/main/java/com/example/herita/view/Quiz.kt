package com.example.herita.view

import com.example.herita.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ==================== Color Palette ====================
object QuizColors {
    val Primary = Color(0xFF1B5E20)
    val Secondary = Color(0xFFD32F2F)
    val BottomBarBg = Color(0xFF3E2723)
    val ButtonPrimary = Color(0xFF000000)
    val ButtonSecondary = Color(0xFFE0E0E0)
    val Correct = Color(0xFF00E676)
    val Wrong = Color(0xFFFF1744)
}

// ==================== Bottom Bar Component ====================
@Composable
fun QuizBottomBar(
    selectedItem: Int = 0,
    onItemSelected: (Int) -> Unit = {}
) {
    NavigationBar(
        containerColor = QuizColors.BottomBarBg,
        tonalElevation = 8.dp,
        modifier = Modifier.height(80.dp)
    ) {
        // Menu
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = if (selectedItem == 0) Color(0xFFBCAAA4) else Color(0xFF6D4C41),
                    modifier = Modifier.size(28.dp)
                )
            },
            selected = selectedItem == 0,
            onClick = { onItemSelected(0) },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent
            )
        )

        // Favorites
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Default.Star,
                    contentDescription = "Favorites",
                    tint = if (selectedItem == 1) Color(0xFFBCAAA4) else Color(0xFF6D4C41),
                    modifier = Modifier.size(28.dp)
                )
            },
            selected = selectedItem == 1,
            onClick = { onItemSelected(1) },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent
            )
        )

        // Add
        NavigationBarItem(
            icon = {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFFD7CCC8),
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Color(0xFF3E2723),
                        modifier = Modifier
                            .padding(12.dp)
                            .size(24.dp)
                    )
                }
            },
            selected = selectedItem == 2,
            onClick = { onItemSelected(2) },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent
            )
        )

        // Stats
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Default.BarChart,
                    contentDescription = "Stats",
                    tint = if (selectedItem == 3) Color(0xFFBCAAA4) else Color(0xFF6D4C41),
                    modifier = Modifier.size(28.dp)
                )
            },
            selected = selectedItem == 3,
            onClick = { onItemSelected(3) },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent
            )
        )

        // Profile
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = if (selectedItem == 4) Color(0xFFBCAAA4) else Color(0xFF6D4C41),
                    modifier = Modifier.size(28.dp)
                )
            },
            selected = selectedItem == 4,
            onClick = { onItemSelected(4) },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent
            )
        )
    }
}

// ==================== Quiz Start Screen Content ====================
@Composable
fun QuizStartContent(
    totalQuestions: Int = 30,
    onStartQuiz: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Total Questions Badge
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = Color(0xFFE0E0E0),
            modifier = Modifier.padding(top = 32.dp)
        ) {
            Text(
                text = "Total Soal: $totalQuestions",
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        // Middle Content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            // Title
            Text(
                text = "Ayo tunjukkan\npengetahuanmu",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 40.sp
            )

            Spacer(modifier = Modifier.height(35.dp))

            // Character Image Placeholder
            Box(
                modifier = Modifier
                    .size(330.dp)
                    .background(Color(0xFFFFFFFF), RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                // Placeholder untuk gambar karakter
//                Icon(
//                    painter = painterResource(id = R.drawable.batak),
//                    contentDescription = null,
//                    modifier = Modifier.size(140.dp),
//                    tint = Color(0xFFBDBDBD)
//                )
                Image(
                    painter = painterResource(id = R.drawable.quizman),
                    contentDescription = "Deskripsi",
                    modifier = Modifier.size(330.dp),
                    contentScale = ContentScale.Crop // atau Fit, FillBounds, dll
                )
            }
        }

        // Start Button
        Button(
            onClick = onStartQuiz,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = QuizColors.ButtonPrimary
            ),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                text = "Mulai Kuis",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

// ==================== Quiz Question Screen Content ====================
@Composable
fun QuizQuestionContent(
    questionNumber: Int = 1,
    totalQuestions: Int = 30,
    timeRemaining: String = "04:57",
    question: String = "Pilih jawaban yang tepat",
    questionDetail: String = "Gambar tersebut merupakan pulau ...",
    options: List<String> = listOf("Sumatera", "Jawa", "Bali", "Papua"),
    selectedOption: Int? = null,
    onOptionSelected: (Int) -> Unit = {},
    onNext: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Timer Badge
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = Color(0xFFE0E0E0),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(
                text = "Sisa Waktu: $timeRemaining",
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Question Title
        Text(
            text = question,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Map/Image Placeholder
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(Color(0xFF4DB6AC), RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            // Placeholder untuk gambar peta
            Icon(
                Icons.Default.Map,
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                tint = Color.White.copy(alpha = 0.7f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Question Detail
        Text(
            text = questionDetail,
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Options Grid
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            options.chunked(2).forEachIndexed { rowIndex, rowOptions ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    rowOptions.forEachIndexed { colIndex, option ->
                        val optionIndex = rowIndex * 2 + colIndex
                        val isSelected = selectedOption == optionIndex

                        Button(
                            onClick = { onOptionSelected(optionIndex) },
                            modifier = Modifier
                                .weight(1f)
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isSelected && optionIndex == 1)
                                    QuizColors.Primary
                                else if (isSelected)
                                    QuizColors.ButtonSecondary
                                else
                                    QuizColors.ButtonSecondary
                            ),
                            shape = RoundedCornerShape(28.dp)
                        ) {
                            Text(
                                text = option,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = if (isSelected && optionIndex == 1) Color.White else Color.Black
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Next Button
        Button(
            onClick = onNext,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = QuizColors.ButtonPrimary
            ),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                text = "Lanjut",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

// ==================== Quiz Review Screen Content ====================
@Composable
fun QuizReviewContent(
    score: Int = 50,
    totalScore: Int = 100,
    questions: List<QuizQuestionResult> = listOf(
        QuizQuestionResult(1, "Apa suku dengan populasi terbesar di indonesia?", "Uciha", true),
        QuizQuestionResult(2, "Apa suku dengan populasi terkecil di indonesia?", "Uciha", false)
    ),
    onFinish: () -> Unit = {},
    onViewExplanation: (Int) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E0))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Score Badge
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = QuizColors.Secondary,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(
                text = "Nilai: $score/$totalScore",
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Questions Review List
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            questions.forEach { question ->
                QuizResultCard(
                    question = question,
                    onViewExplanation = { onViewExplanation(question.number) }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Finish Button
        Button(
            onClick = onFinish,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = QuizColors.ButtonPrimary
            ),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                text = "Selesai",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun QuizResultCard(
    question: QuizQuestionResult,
    onViewExplanation: () -> Unit = {}
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            // Header with question number and status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Soal ${question.number}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = if (question.isCorrect) QuizColors.Correct else QuizColors.Wrong
                ) {
                    Text(
                        text = if (question.isCorrect) "Benar" else "Salah",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Question Text
            Text(
                text = question.text,
                fontSize = 14.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            // User Answer
            Text(
                text = "Jawabanmu: ${question.userAnswer}",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            // View Explanation Link
            TextButton(
                onClick = onViewExplanation,
                modifier = Modifier.align(Alignment.End),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "Lihat penjelasan",
                    fontSize = 13.sp,
                    color = Color.Black,
                    textDecoration = androidx.compose.ui.text.style.TextDecoration.Underline
                )
            }
        }
    }
}

data class QuizQuestionResult(
    val number: Int,
    val text: String,
    val userAnswer: String,
    val isCorrect: Boolean
)

// ==================== Quiz Completed Screen Content ====================
@Composable
fun QuizCompletedContent(
    completedTime: String = "06:20:39",
    onViewDiscussion: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Completed Time Badge
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = Color(0xFFE0E0E0),
            modifier = Modifier.padding(top = 32.dp)
        ) {
            Text(
                text = "Kuis berikutnya: $completedTime",
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }

        // Middle Content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            // Title
            Text(
                text = "Quiz hari ini sudah\ndikerjakan",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 36.sp
            )

            Spacer(modifier = Modifier.height(35.dp))

            // Character Image Placeholder (sleeping)
            Box(
                modifier = Modifier
                    .size(330.dp)
                    .background(Color(0xFFFFFFFF), RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                // Placeholder untuk gambar karakter tidur
                Image(
                    painter = painterResource(id = R.drawable.sleep),
                    contentDescription = "Deskripsi",
                    modifier = Modifier.size(330.dp),
                    contentScale = ContentScale.Crop // atau Fit, FillBounds, dll
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Subtitle
            Text(
                text = "Tunggu besok untuk kuis berikutnya",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }

        // View Discussion Button
        Button(
            onClick = onViewDiscussion,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = QuizColors.ButtonPrimary
            ),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                text = "Lihat Pembahasan",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

// ==================== Complete Screens with Scaffold ====================
@Composable
fun QuizStartScreen(
    totalQuestions: Int = 30,
    selectedBottomItem: Int = 0,
    onStartQuiz: () -> Unit = {},
    onBottomItemSelected: (Int) -> Unit = {}
) {
    Scaffold(
        bottomBar = {
            QuizBottomBar(
                selectedItem = selectedBottomItem,
                onItemSelected = onBottomItemSelected
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            QuizStartContent(
                totalQuestions = totalQuestions,
                onStartQuiz = onStartQuiz
            )
        }
    }
}

@Composable
fun QuizQuestionScreen(
    questionNumber: Int = 1,
    totalQuestions: Int = 30,
    timeRemaining: String = "04:57",
    question: String = "Pilih jawaban yang tepat",
    questionDetail: String = "Gambar tersebut merupakan pulau ...",
    options: List<String> = listOf("Sumatera", "Jawa", "Bali", "Papua"),
    selectedOption: Int? = 1,
    onOptionSelected: (Int) -> Unit = {},
    onNext: () -> Unit = {}
) {
    // No bottom bar for question screen
    QuizQuestionContent(
        questionNumber = questionNumber,
        totalQuestions = totalQuestions,
        timeRemaining = timeRemaining,
        question = question,
        questionDetail = questionDetail,
        options = options,
        selectedOption = selectedOption,
        onOptionSelected = onOptionSelected,
        onNext = onNext
    )
}

@Composable
fun QuizReviewScreen(
    score: Int = 50,
    totalScore: Int = 100,
    questions: List<QuizQuestionResult> = listOf(
        QuizQuestionResult(1, "Apa suku dengan populasi terbesar di indonesia?", "Uciha", true),
        QuizQuestionResult(2, "Apa suku dengan populasi terkecil di indonesia?", "Uciha", false)
    ),
    onFinish: () -> Unit = {},
    onViewExplanation: (Int) -> Unit = {}
) {
    // No bottom bar for review screen
    QuizReviewContent(
        score = score,
        totalScore = totalScore,
        questions = questions,
        onFinish = onFinish,
        onViewExplanation = onViewExplanation
    )
}

@Composable
fun QuizCompletedScreen(
    completedTime: String = "06:20:39",
    selectedBottomItem: Int = 0,
    onViewDiscussion: () -> Unit = {},
    onBottomItemSelected: (Int) -> Unit = {}
) {
    Scaffold(
        bottomBar = {
            QuizBottomBar(
                selectedItem = selectedBottomItem,
                onItemSelected = onBottomItemSelected
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            QuizCompletedContent(
                completedTime = completedTime,
                onViewDiscussion = onViewDiscussion
            )
        }
    }
}

// ==================== Previews ====================
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewQuizStartScreen() {
    QuizStartScreen()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewQuizQuestionScreen() {
    QuizQuestionScreen()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewQuizReviewScreen() {
    QuizReviewScreen()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewQuizCompletedScreen() {
    QuizCompletedScreen()
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomBar() {
    QuizBottomBar()
}