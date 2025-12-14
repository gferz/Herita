package com.example.herita.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.herita.data.local.AppDatabase
import com.example.herita.data.local.UserDao
import com.example.herita.data.local.UserEntity
import com.example.herita.data.repository.UserRepository
import com.example.herita.view.LoadingScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class InitDataState {
    object Loading : InitDataState()
    object NoUser : InitDataState()
    data class HasUser(val user: UserEntity) : InitDataState()
}

class InitViewModel(application: Application) : AndroidViewModel(application){
    private val _initDataState = MutableStateFlow<InitDataState>(InitDataState.Loading)
    val initDataState: StateFlow<InitDataState> = _initDataState.asStateFlow()

    private val userRepository: UserRepository

    init {
        val database = AppDatabase.getInstance(application)
        userRepository = UserRepository(database.userDao())
        viewModelScope.launch {
            userRepository.getUser().collect { user ->
                _initDataState.value = if (user != null) {
                    InitDataState.HasUser(user)
                } else {
                    InitDataState.NoUser
                }
            }
        }
    }

    fun insertUser(name: String, age: Int) = viewModelScope.launch {
        userRepository.createUser(name, age)
    }

    fun clearUser() = viewModelScope.launch {
        userRepository.clearUser()
    }
}