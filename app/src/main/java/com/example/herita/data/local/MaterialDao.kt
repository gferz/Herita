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
}