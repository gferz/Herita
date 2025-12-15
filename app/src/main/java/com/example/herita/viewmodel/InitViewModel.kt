package com.example.herita.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.herita.data.local.AppDatabase
import com.example.herita.data.local.UserEntity
import com.example.herita.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class InitDataState {
    object Loading : InitDataState()
    object NoUser : InitDataState()
    data class HasUser(val user: UserEntity) : InitDataState()
    data class Error(val message: String) : InitDataState()
}


class InitViewModel(application: Application) : AndroidViewModel(application){
    private val _initDataState = MutableStateFlow<InitDataState>(InitDataState.Loading)
    val initDataState: StateFlow<InitDataState> = _initDataState.asStateFlow()


    private var _userData: UserEntity? = null

    private val userRepository: UserRepository

    init {
        val database = AppDatabase.getInstance(application)
        userRepository = UserRepository(database.userDao())
        viewModelScope.launch {
            userRepository.getUser().collect { user ->
                if(user != null){
                    _initDataState.value = InitDataState.HasUser(user)
                    _userData = user
                    Log.d("INIT", user.name)
                }else{
                    _initDataState.value = InitDataState.NoUser
                }
            }
        }
    }

    fun getUser(): UserEntity? {
        return _userData
    }

    fun insertUser(name: String, age: Int) = viewModelScope.launch {
        _initDataState.value = InitDataState.Loading

        userRepository.createUser(name, age)

        userRepository.getUser().collect { user ->

            if(user != null){
                _initDataState.value = InitDataState.HasUser(user)
                _userData = user
            }else{
                InitDataState.Error("Something Wrong")
            }
        }
    }

    fun clearUser() = viewModelScope.launch {
        userRepository.clearUser()
    }
}