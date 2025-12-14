package com.example.herita.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    val id: Int = 1,          // single user, selalu 1
    val name: String,
    val age: Int,
    val createdAt: Long = System.currentTimeMillis()
)