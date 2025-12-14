package com.example.herita.data.repository

import android.content.Context
import com.example.herita.data.local.TribeDao
import com.example.herita.data.local.TribeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.Flow

class TribeRepository(
    private val tribeDao: TribeDao
) {
    fun observeTribes(): Flow<List<TribeEntity>> =
        tribeDao.getAllTribes()

    suspend fun insert(tribe: TribeEntity) =
        tribeDao.insertTribe(tribe)
}
