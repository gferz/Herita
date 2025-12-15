package com.example.herita

import android.app.Application
import com.example.herita.data.local.AppDatabase
import com.example.herita.data.repository.SeedDataLoader
import kotlinx.coroutines.CoroutineScope
import com.example.herita.data.repository.SeedMaterialLoader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HeritaApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val db = AppDatabase.getInstance(this)
        val tribeDao = db.tribeDao()

        CoroutineScope(Dispatchers.IO).launch {
            if (tribeDao.getCount() == 0) {
                val tribes = SeedDataLoader.loadTribesFromAssets(this@HeritaApp)
                tribeDao.insertTribes(tribes)
            }
        }

        val materialDao = db.materialDao()

        CoroutineScope(Dispatchers.IO).launch {
            if (materialDao.getCount() == 0) {
                val materials = SeedMaterialLoader.loadMaterialsFromAssets(this@HeritaApp)
                materialDao.insertMaterials(materials)
            }
        }
    }
}
