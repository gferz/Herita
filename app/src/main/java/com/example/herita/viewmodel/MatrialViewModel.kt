package com.example.herita.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.herita.data.local.AppDatabase
import com.example.herita.data.local.MaterialEntity
import com.example.herita.data.repository.MaterialRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MaterialViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MaterialRepository

    private val _uiState = MutableStateFlow<MaterialUiState>(MaterialUiState.Loading)
    val uiState: StateFlow<MaterialUiState> = _uiState.asStateFlow()

    init {
        val database = AppDatabase.getInstance(application)
        val materialDao = database.materialDao()
        repository = MaterialRepository(materialDao)
    }

    /**
     * Load material berdasarkan tribeId dan topic
     */
    fun loadMaterials(tribeId: String, topic: String) {
        viewModelScope.launch {
            _uiState.value = MaterialUiState.Loading

            try {
                repository.getMaterials(tribeId, topic).collect { materials ->
                    _uiState.value = if (materials.isEmpty()) {
                        MaterialUiState.Empty
                    } else {
                        MaterialUiState.Success(materials)
                    }
                }
            } catch (e: Exception) {
                _uiState.value = MaterialUiState.Error(
                    e.message ?: "Terjadi kesalahan saat memuat data"
                )
            }
        }
    }
}

/**
 * UI State untuk Material Screen
 */
sealed class MaterialUiState {
    object Loading : MaterialUiState()
    object Empty : MaterialUiState()
    data class Success(val materials: List<MaterialEntity>) : MaterialUiState()
    data class Error(val message: String) : MaterialUiState()
}