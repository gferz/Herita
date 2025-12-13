package com.example.herita.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Color definitions
private val BrownDark = Color(0xFF4A3428)
private val BrownMedium = Color(0xFF6B4E3D)
private val OffWhite = Color(0xFFF5F5F5)

@Composable
fun TribeSelectionScreen(
    currentTribe: String = "Batak",
    progress: Int = 50,
    onPreviousClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
    onSelectClick: () -> Unit = {}
) {
    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(OffWhite)
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Progress indicator
            Surface(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .shadow(4.dp, RoundedCornerShape(24.dp)),
                shape = RoundedCornerShape(24.dp),
                color = Color.White
            ) {
                Text(
                    text = "Progress: $progress%",
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Title
            Text(
                text = "Pilih suku untuk\nmulai belajar",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 36.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Character carousel
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Previous arrow
                IconButton(
                    onClick = onPreviousClick,
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_media_previous),
                        contentDescription = "Previous",
                        modifier = Modifier.size(32.dp)
                    )
                }

                // Character display
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Placeholder for character image
                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .background(Color.LightGray.copy(alpha = 0.3f), RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Character\nImage",
                            textAlign = TextAlign.Center,
                            color = Color.Gray
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = currentTribe,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Next arrow
                IconButton(
                    onClick = onNextClick,
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_media_next),
                        contentDescription = "Next",
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Select button
            Button(
                onClick = onSelectClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Text(
                    text = "Pilih",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun LearningMaterialScreen(
    title: String = "Sejarah",
    content: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam scelerisque tortor sed erat imperdiet, sit amet viverra ipsum sagittis. Maecenas vitae ex id risus tempor porta convallis et nisi. Phasellus non dolor nunc. Aliquam pretium justo placerat pharetra fringilla. Donec scelerisque mauris nec interdum fermentum. Ut ut orci placerat metus venenatis molestie. In nec justo tortor. Maecenas lacus velit, condimentum eu placerat sit amet, venenatis et turpis.",
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(OffWhite)
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Title
            Text(
                text = title,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Content card
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .shadow(4.dp, RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                color = Color.White
            ) {
                Text(
                    text = content,
                    modifier = Modifier.padding(24.dp),
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Back button
            Button(
                onClick = onBackClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BrownMedium
                )
            ) {
                Text(
                    text = "Kembali",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun TopicSelectionScreen(
    topics: List<String> = listOf(
        "Adat istiadat",
        "Baju adat",
        "Sejarah",
        "Hari raya",
        "Prasasti"
    ),
    selectedTopic: String = "Sejarah",
    onTopicClick: (String) -> Unit = {},
    onSelectClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(OffWhite)
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Title
            Text(
                text = "Pilih topik",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Character placeholder
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .background(Color.LightGray.copy(alpha = 0.3f), RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Character\nImage",
                        textAlign = TextAlign.Center,
                        color = Color.Gray
                    )
                }

                // Topics list
                Column(
                    modifier = Modifier.padding(start = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    topics.forEach { topic ->
                        TopicButton(
                            text = topic,
                            isSelected = topic == selectedTopic,
                            onClick = { onTopicClick(topic) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Select button
            Button(
                onClick = onSelectClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Text(
                    text = "Pilih",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Back button
            Button(
                onClick = onBackClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BrownMedium
                )
            ) {
                Text(
                    text = "Kembali",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun TopicButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(160.dp)
            .height(48.dp),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color.Black else Color.White,
            contentColor = if (isSelected) Color.White else Color.Black
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = if (isSelected) 0.dp else 2.dp
        )
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}

@Composable
fun BottomNavigationBar() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp),
        color = BrownDark,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_sort_by_size),
                    contentDescription = "Menu",
                    tint = Color(0xFFB88B7D),
                    modifier = Modifier.size(28.dp)
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = android.R.drawable.btn_star_big_on),
                    contentDescription = "Favorites",
                    tint = Color(0xFFB88B7D),
                    modifier = Modifier.size(28.dp)
                )
            }
            FloatingActionButton(
                onClick = { },
                containerColor = Color.White,
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_input_add),
                    contentDescription = "Add",
                    tint = BrownDark
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_sort_alphabetically),
                    contentDescription = "Stats",
                    tint = Color(0xFFB88B7D),
                    modifier = Modifier.size(28.dp)
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_my_calendar),
                    contentDescription = "Profile",
                    tint = Color(0xFFB88B7D),
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}

// Previews
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TribeSelectionScreenPreview() {
    MaterialTheme {
        TribeSelectionScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LearningMaterialScreenPreview() {
    MaterialTheme {
        LearningMaterialScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TopicSelectionScreenPreview() {
    MaterialTheme {
        TopicSelectionScreen()
    }
}