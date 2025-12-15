package com.example.herita

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.herita.view.CustomBottomNavigationBar
import com.example.herita.view.HomeContent
import com.example.herita.view.LoadingScreen
import com.example.herita.view.MaterialScreen
import com.example.herita.view.QuizCompletedContent
import com.example.herita.view.QuizReviewScreen
import com.example.herita.view.QuizStartContent
import com.example.herita.view.RegisterScreen
import com.example.herita.view.TopicSelectionContent
import com.example.herita.view.TribeScreen
import com.example.herita.viewmodel.InitDataState
import com.example.herita.viewmodel.InitViewModel

@Composable
fun App(viewModel: InitViewModel = viewModel() ){
    val navController = rememberNavController()

    val dataState by viewModel.initDataState.collectAsState()

    var goToHome = false

    when(val state = dataState){
        is InitDataState.HasUser -> {
            goToHome = true
        }
        is InitDataState.NoUser ->{
            RegisterScreen{name, age ->
                viewModel.insertUser(name, age.toInt())
            }
        }
        is InitDataState.Loading -> LoadingScreen()
        is InitDataState.Error -> {}
    }

    if (goToHome) {
        var selectedIndex by remember { mutableIntStateOf(1) }

        Scaffold(
            modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.systemBars),
            bottomBar = {
                CustomBottomNavigationBar(
                    selectedIndex = selectedIndex,
                    onItemSelected = { index ->
                        if (selectedIndex != index){
                            selectedIndex = index

                            when(selectedIndex){
                                0 -> {navController.navigate("quiz")}
                                1 -> {navController.navigate("home")}
                                2 -> {navController.navigate("tribe")}
                            }
                        }
                    }
                )
            }
        ){ paddingValues ->
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(paddingValues)
            ){

                composable("home"){
                    HomeContent(
                        userName = viewModel.getUser()?.name ?: "",
                        onBelajarClick = {
                            selectedIndex = 2
                            navController.navigate("tribe")
                        },
                        onKuisClick = {
                            selectedIndex = 0
                            navController.navigate("quiz")
                        }
                    )
                }

                composable(
                    route = "material/{tribeName}/{topic}",
                    arguments = listOf(
                        navArgument("tribeName") {
                            type = NavType.StringType
                        },
                        navArgument("topic"){
                            type = NavType.StringType
                        }
                    )
                ){ backStackEntry ->
                    val tribeName = backStackEntry.arguments?.getString("tribeName") ?: ""
                    val topic = backStackEntry.arguments?.getString("topic") ?: ""

                    MaterialScreen(tribeId = tribeName, topic = topic, onBackPressed = { navController.navigate("tribe") })
                }

                composable("tribe") {
                    TribeScreen(
                        onTribeSelected = { tribe ->
                            val path: String = "topic/" + tribe.tribeId
                            navController.navigate(path)
                        }
                    )
                }

                composable(
                    route = "topic/{tribeName}",
                    arguments = listOf(
                        navArgument("tribeName") {
                            type = NavType.StringType
                        }
                    )
                ){ backStackEntry ->
                    val tribeName = backStackEntry.arguments?.getString("tribeName") ?: ""
                    TopicSelectionContent(
                        tribe = tribeName,
                        onBackClick = {
                            navController.navigate("tribe")
                        },
                        onTopicSelected = {tribeId, topic ->
                            val path: String = "material/$tribeId/$topic"
                            navController.navigate(path)
                        },
                    )
                }

                composable("quiz"){
                    QuizStartContent(
                        totalQuestions = 30,
                        onStartQuiz = {
                            navController.navigate("review")
                        }
                    )

                    QuizCompletedContent(
                        onViewDiscussion = {navController.navigate("review")}
                    )
                }

                composable("review"){
                    QuizReviewScreen(onFinish = {
                        selectedIndex = 1
                        navController.navigate("home")
                    })
                }

            }
        }


    }
}