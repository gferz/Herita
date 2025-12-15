package com.example.herita.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.herita.data.local.TribeEntity
import com.example.herita.viewmodel.TribeUiState
import com.example.herita.viewmodel.TribeViewModel

@Composable
fun TribeScreen(
    viewModel: TribeViewModel = viewModel(),
    onTribeSelected: (TribeEntity) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        is TribeUiState.Loading -> {
            LoadingScreen()
        }
        is TribeUiState.Success -> {
            TribeSelectionContent(
                tribes = state.tribes,
                progress = 50,
                onSelectClick = { selectedTribe ->
                    onTribeSelected(selectedTribe)
                }
            )
        }
        is TribeUiState.Error -> {

        }
    }
}