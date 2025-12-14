package com.example.herita.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TribeDao {

    @Query("SELECT * FROM tribe ORDER BY name")
    fun getAllTribes(): Flow<List<TribeEntity>>

    @Query("SELECT COUNT(*) FROM tribe")
    suspend fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTribe(tribe: TribeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTribes(list: List<TribeEntity>)

    @Query("DELETE FROM tribe")
    suspend fun clearAllTribes()
}

