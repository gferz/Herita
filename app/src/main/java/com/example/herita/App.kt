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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.herita.view.CustomBottomNavigationBar
import com.example.herita.view.LoadingScreen
import com.example.herita.view.QuizStartContent
import com.example.herita.view.RegisterScreen
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
        var selectedIndex by remember { mutableIntStateOf(0) }

        Scaffold(
            modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.systemBars),
            bottomBar = {
                CustomBottomNavigationBar(
                    selectedIndex = selectedIndex,
                    onItemSelected = { index ->
                        selectedIndex = index
                    }
                )
            }
        ){ paddingValues ->
            NavHost(
                navController = navController,
                startDestination = "quiz",
                modifier = Modifier.padding(paddingValues)
            ){
                composable("register"){}

                composable("home"){

                }

                composable("add"){}

                composable("learn"){}

                composable("quiz"){
                    QuizStartContent(
                        totalQuestions = 30,
                        onStartQuiz = {}
                    )
                }

                composable("profile"){}

                composable("list"){}
            }
        }


    }
}