package com.example.herita.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MaterialDao {

    @Query("""
        SELECT * FROM material
        WHERE tribeId = :tribeId
        AND topic = :topic
        ORDER BY title
    """)
    fun getMaterials(
        tribeId: String,
        topic: String
    ): Flow<List<MaterialEntity>>

    @Query("SELECT * FROM material WHERE materialId = :materialId LIMIT 1")
    fun getMaterialById(materialId: String): Flow<MaterialEntity?>

    // 🔹 Dipakai untuk seed dari material.json
    @Query("SELECT COUNT(*) FROM material")
    suspend fun getCount(): Int

    // 🔹 Opsional (debug / future use)
    @Query("SELECT * FROM material ORDER BY tribeId, topic, title")
    fun getAllMaterials(): Flow<List<MaterialEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMaterials(materials: List<MaterialEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMaterial(material: MaterialEntity)

    @Query("DELETE FROM material WHERE tribeId = :tribeId AND topic = :topic")
    suspend fun clearMaterialsForTopic(
        tribeId: String,
        topic: String
    )
    /**
     * Menandai satu material sebagai selesai
     * Dipanggil saat tombol "Akhiri Pembelajaran"
     */
    @Query("""
        UPDATE material
        SET isCompleted = 1
        WHERE materialId = :materialId
    """)
    suspend fun markMaterialCompleted(materialId: String)

    /**
     * Menghitung jumlah material yang sudah selesai
     * untuk satu suku tertentu
     */
    @Query("""
        SELECT COUNT(*) FROM material
        WHERE tribeId = :tribeId
        AND isCompleted = 1
    """)
    suspend fun countCompletedByTribe(tribeId: String): Int

    /**
     * (Opsional tapi rapi)
     * Reset progress belajar satu suku
     */
    @Query("""
        UPDATE material
        SET isCompleted = 0
        WHERE tribeId = :tribeId
    """)
    suspend fun resetProgressForTribe(tribeId: String)
}