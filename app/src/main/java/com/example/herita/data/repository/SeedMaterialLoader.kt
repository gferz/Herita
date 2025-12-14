package com.example.herita.data.repository

import android.content.Context
import com.example.herita.data.local.MaterialEntity
import com.example.herita.data.remote.MaterialJson
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

object SeedMaterialLoader {

    fun loadMaterialsFromAssets(context: Context): List<MaterialEntity> {
        context.assets.open("material.json").use { stream ->
            InputStreamReader(stream).use { reader ->
                val type = object : TypeToken<List<MaterialJson>>() {}.type
                val jsonList: List<MaterialJson> =
                    Gson().fromJson(reader, type)

                return jsonList.map {
                    MaterialEntity(
                        materialId = it.materialId,
                        tribeId = it.tribeId,
                        topic = it.topic,
                        title = it.title ?: "",
                        content = it.content,
                        isCompleted = false   // 🔑 PENTING untuk progress
                    )
                }
            }
        }
    }
}

