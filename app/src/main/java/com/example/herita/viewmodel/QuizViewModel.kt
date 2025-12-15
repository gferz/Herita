package com.example.herita.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.herita.data.repository.QuizRepository
import com.example.herita.data.repository.QuizReviewData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class QuizUiState {
    object Loading : QuizUiState()
    data class Review(val data: QuizReviewData) : QuizUiState()
    data class Error(val message: String) : QuizUiState()
}

class QuizViewModel(
    private val repository: QuizRepository = QuizRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<QuizUiState>(QuizUiState.Loading)
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    init {
        loadQuizReviews()
    }

    fun loadQuizReviews() {
        viewModelScope.launch {
            _uiState.value = QuizUiState.Loading
            try {
                val reviews = repository.getQuizReviews()
                _uiState.value = QuizUiState.Review(reviews)
            } catch (e: Exception) {
                _uiState.value = QuizUiState.Error(
                    message = e.message ?: "Terjadi kesalahan saat memuat data"
                )
            }
        }
    }

    fun retry() {
        loadQuizReviews()
    }
}