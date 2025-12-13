package com.example.herita.viewmodel

import android.os.Message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class TopicDataState {
    object Loading: TopicDataState()
    data class Success(val topicList: List<String>): TopicDataState()
    data class Error(val message: String) : TopicDataState()
}

class TopicSelectionViewModel(
//    private val tribe: String
) : ViewModel(){
    private val _uiState = MutableStateFlow<TopicDataState>(TopicDataState.Loading)
    val uiState: StateFlow<TopicDataState> = _uiState.asStateFlow()

    private fun fetchData(){
        viewModelScope.launch {
            _uiState.value = TopicDataState.Loading

            try{
//                val data = repository.get()
//                _uiState.value = TopicDataState.Success(data)
            } catch(e: Exception){
                _uiState.value = TopicDataState.Error(e.message ?: "unknown")
            }
        }

        fun retry() {
            fetchData()
        }
    }
}