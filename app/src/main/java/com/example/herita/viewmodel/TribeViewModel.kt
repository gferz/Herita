package com.example.herita.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.herita.data.local.AppDatabase
import com.example.herita.data.local.TribeEntity
import com.example.herita.data.repository.TribeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class TribeUiState {
    object Loading : TribeUiState()
    data class Success(val tribes: List<TribeEntity>) : TribeUiState()
    data class Error(val message: String) : TribeUiState()
}

class TribeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TribeRepository = TribeRepository(
        tribeDao = AppDatabase.getInstance(application).tribeDao()
    )

    private val _uiState = MutableStateFlow<TribeUiState>(TribeUiState.Loading)
    val uiState: StateFlow<TribeUiState> = _uiState.asStateFlow()

    init {
        loadTribes()
    }

    private fun loadTribes() {
        viewModelScope.launch {
            try {
                repository.observeTribes().collect { tribes ->
                    _uiState.value = TribeUiState.Success(tribes)
                }
            } catch (e: Exception) {
                _uiState.value = TribeUiState.Error(
                    message = e.message ?: "Unknown error occurred"
                )
            }
        }
    }
}