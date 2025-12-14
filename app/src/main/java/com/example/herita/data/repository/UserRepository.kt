package com.example.herita.data.repository

import com.example.herita.data.local.UserDao
import com.example.herita.data.local.UserEntity
import kotlinx.coroutines.flow.Flow

class UserRepository(
    private val userDao: UserDao
) {

    fun getUser(): Flow<UserEntity?> {
        return userDao.getUser()
    }

    /**
     * Dipakai saat pertama kali daftar
     */
    suspend fun createUser(
        name: String,
        age: Int
    ) {
        userDao.insertUser(
            UserEntity(
                name = name,
                age = age
            )
        )
    }

    /**
     * 🔥 Dipakai saat user mengubah profil
     */
    suspend fun updateUser(
        name: String,
        age: Int
    ) {
        userDao.updateUser(
            name = name,
            age = age
        )
    }

    suspend fun clearUser() {
        userDao.clearUser()
    }
}
