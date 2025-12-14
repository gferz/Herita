package com.example.herita.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "material")
data class MaterialEntity(
    @PrimaryKey val materialId: String,
    val tribeId: String,
    val topic: String,
    val title: String,
    val content: String,

    val isCompleted: Boolean = false
)
