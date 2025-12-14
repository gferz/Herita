package com.example.herita.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tribe")
data class TribeEntity(
    @PrimaryKey val tribeId: String,
    val name: String,
    val description: String,
    val imageName: String
)
