package com.example.herita.data.repository

import com.example.herita.data.local.MaterialDao
import com.example.herita.data.local.MaterialEntity
import kotlinx.coroutines.flow.Flow

class MaterialRepository(
    private val materialDao: MaterialDao
) {

    /**
     * Ambil daftar material berdasarkan suku & topic
     */
    fun getMaterials(
        tribeId: String,
        topic: String
    ): Flow<List<MaterialEntity>> {
        return materialDao.getMaterials(tribeId, topic)
    }

    /**
     * Dipanggil saat tombol "Akhiri Pembelajaran"
     */
    suspend fun completeMaterial(materialId: String) {
        materialDao.markMaterialCompleted(materialId)
    }

    suspend fun getProgressForTribe(tribeId: String): Float {
        val completed = materialDao.countCompletedByTribe(tribeId)
        val totalMaterialPerTribe = 5
        return completed.toFloat() / totalMaterialPerTribe.toFloat()
    }
}
