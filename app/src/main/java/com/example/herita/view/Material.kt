package com.example.herita.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.herita.viewmodel.MaterialUiState
import com.example.herita.viewmodel.MaterialViewModel

@Composable
fun MaterialScreen(
    tribeId: String,
    topic: String,
    onBackPressed: () -> Unit,
    viewModel: MaterialViewModel = viewModel()
) {
    // Load materials saat screen pertama kali ditampilkan
    LaunchedEffect(tribeId, topic) {
        viewModel.loadMaterials(tribeId, topic)
    }

    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        is MaterialUiState.Loading -> {
            LoadingScreen()
        }

        is MaterialUiState.Empty -> {

        }

        is MaterialUiState.Success -> {
            LearningMaterialScreen(
                title = state.materials[0].title
            )
        }

        is MaterialUiState.Error -> {
        }
    }
}
