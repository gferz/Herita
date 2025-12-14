package com.example.herita.data.repository

import android.content.Context
import com.example.herita.data.local.TribeEntity
import com.example.herita.data.remote.TribeJson
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

object SeedDataLoader {

    fun loadTribesFromAssets(context: Context): List<TribeEntity> {
        context.assets.open("tribes.json").use { stream ->
            InputStreamReader(stream).use { reader ->

                val type = object : TypeToken<List<TribeJson>>() {}.type
                val jsonList: List<TribeJson> =
                    Gson().fromJson(reader, type)

                return jsonList.map {
                    TribeEntity(
                        tribeId = it.tribeId,
                        name = it.name,
                        description = it.description,
                        imageName = it.imageName
                    )
                }
            }
        }
    }
}
