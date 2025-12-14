package com.example.herita.data.local

import android.content.Context
import com.example.herita.R
import com.example.herita.ui.model.TribeUi

fun TribeEntity.toUi(context: Context): TribeUi {
    val imageName = this.imageName ?: ""
    val resId = if (imageName.isNotBlank()) {
        context.resources.getIdentifier(
            imageName,
            "drawable",
            context.packageName
        )
    } else 0

    val drawableId = if (resId != 0) resId else R.drawable.batak // fallback

    return TribeUi(
        tribeId = this.tribeId,   // ⬅️ INI KUNCI PERBAIKAN
        name = this.name,
        imageRes = drawableId
    )
}
