package com.example.herita

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.herita.data.local.UserEntity
import com.example.herita.view.LoadingScreen
import com.example.herita.viewmodel.InitDataState
import com.example.herita.viewmodel.InitViewModel

@Composable
fun Splash(
    viewModel: InitViewModel = viewModel(),
    onNavigateToHome: (UserEntity) -> Unit,
    onNavigateToRegister: () -> Unit
){
    val dataState by viewModel.initDataState.collectAsState()

    LaunchedEffect(dataState) {
        when(val state = dataState){
            is InitDataState.HasUser -> onNavigateToHome(state.user)
            is InitDataState.NoUser -> onNavigateToRegister()
            is InitDataState.Loading -> {}
        }
    }

    LoadingScreen()
}

@Composable
fun App(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash",
    ){
        composable("splash") {
            Splash(
                onNavigateToHome = {user -> },
                onNavigateToRegister = {}
            )
        }

        composable("register"){}

        composable("home"){}

        composable("add"){}

        composable("learn"){}

        composable("quiz"){}

        composable("profile"){}

        composable("list"){}

    }
}