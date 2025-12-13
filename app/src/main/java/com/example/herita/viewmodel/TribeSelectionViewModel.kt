package com.example.herita.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class TribeDataState {
    object Loading: TribeDataState()
    data class Success(val tribe: String): TribeDataState()
    data class Error(val message: String) : TribeDataState()
}

class TribeSelectionViewModel(
) : ViewModel(){
    private val _uiState = MutableStateFlow<TribeDataState>(TribeDataState.Loading)
    val uiState: StateFlow<TribeDataState> = _uiState.asStateFlow()

    private fun fetchData(){
        viewModelScope.launch {
            _uiState.value = TribeDataState.Loading

            try{
//                val data = repository.get()
//                _uiState.value = TopicDataState.Success(data)
            } catch(e: Exception){
                _uiState.value = TribeDataState.Error(e.message ?: "unknown")
            }
        }

        fun retry() {
            fetchData()
        }
    }
}