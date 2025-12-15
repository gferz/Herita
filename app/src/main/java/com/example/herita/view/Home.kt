package com.example.herita.view

import com.example.herita.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeContent(
    userName: String = "Gabriel",
    onBelajarClick: () -> Unit = {},
    onKuisClick: () -> Unit = {},
    onReviewClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Greeting Section with Character
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Greeting Text
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Halo,",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
                Text(
                    text = userName,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            // Character Image
            // Replace R.drawable.character_gabriel with your actual drawable resource
            Image(
                painter = painterResource(id = R.drawable.hello),
                contentDescription = "Character Gabriel",
                modifier = Modifier.size(180.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Motivational Text
        Text(
            text = "Ayo kerjakan sesuatu!",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Action Buttons
        DashboardActionButton(
            title = "Belajar",
            description = "Mempelajari topik baru\nyang menarik",
            gradientColors = listOf(
                Color(0xFF8B2635),
                Color(0xFFE8604D)
            ),
            onClick = onBelajarClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        DashboardActionButton(
            title = "Kuis",
            description = "Uji kemampuanmu untuk\ntopik yang telah dipelajari",
            gradientColors = listOf(
                Color(0xFF7A1F2D),
                Color(0xFFE8604D)
            ),
            onClick = onKuisClick
        )

        Spacer(modifier = Modifier.height(16.dp))

//        DashboardActionButton(
//            title = "Review",
//            description = "Melihat kembali Topik dan\nKuis yang telah dipelajari",
//            gradientColors = listOf(
//                Color(0xFF6B1A28),
//                Color(0xFFE8604D)
//            ),
//            onClick = onReviewClick
//        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun DashboardActionButton(
    title: String,
    description: String,
    gradientColors: List<Color>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp),
        shape = RoundedCornerShape(40.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(0.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = gradientColors
                    ),
                    shape = RoundedCornerShape(40.dp)
                )
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = description,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White.copy(alpha = 0.95f),
                    lineHeight = 16.sp
                )
            }
        }
    }
}

// Preview untuk development
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeContentPreview() {
    MaterialTheme {
        HomeContent(
            userName = "Gabriel",
            onBelajarClick = {},
            onKuisClick = {},
            onReviewClick = {}
        )
    }
}