package com.example.herita.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val BottomNavBackground = Color(0xFF3D2622)
private val SelectedButtonBackground = Color(0xFFF5EDE7)

data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector? = null,
    val iconResId: Int? = null,
)

val bottomNavItemList = listOf(
//    BottomNavItem(route = "List", label = "list", icon = Icons.AutoMirrored.Filled.List),
    BottomNavItem(route = "Quiz", label = "quiz", icon = Icons.Default.Quiz),
    BottomNavItem(route = "Home", label = "home" ,icon = Icons.Default.Home),
    BottomNavItem(route = "Learn", label = "learn", icon = Icons.AutoMirrored.Filled.Assignment),
//    BottomNavItem(route = "Profile", label = "profile", icon = Icons.Default.Person)
)


@Composable
fun BottomNavItemView(
    item: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit
){
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(56.dp)
    ) {
        if(isSelected){
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(SelectedButtonBackground),
                contentAlignment = Alignment.Center
            ){
                when{
                    item.icon != null -> {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    item.iconResId != null -> {
                        Icon(
                            painter = painterResource(id = item.iconResId),
                            contentDescription = item.label,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }
        }else {
            // Support both ImageVector and drawable resource
            when {
                item.icon != null -> {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.size(28.dp)
                    )
                }
                item.iconResId != null -> {
                    Icon(
                        painter = painterResource(id = item.iconResId),
                        contentDescription = item.label,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CustomBottomNavigationBar(
    modifier: Modifier = Modifier,
    selectedIndex: Int = 0,
    onItemSelected: (Int) -> Unit = {},
    navigateToRoute: (String) -> Unit = {}
) {
    val items = bottomNavItemList

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.White)
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .background(BottomNavBackground)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                BottomNavItemView(
                    item = item,
                    isSelected = selectedIndex == index,
                    onClick = {
                        onItemSelected(index)
                        navigateToRoute(item.route)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun BottomNavigationBarPreview() {
    var selectedIndex by remember { mutableIntStateOf(0) }

    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            // Empty content area
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 80.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Content Area",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Gray
                )
            }

            // Bottom Navigation Bar
            CustomBottomNavigationBar(
                modifier = Modifier.align(Alignment.BottomCenter),
                selectedIndex = selectedIndex,
                onItemSelected = { index ->
                    selectedIndex = index
                }
            )
        }
    }
}