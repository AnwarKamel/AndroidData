package com.anwarkamel.ouail.androiddata.data

import com.anwarkamel.ouail.androiddata.IMAGE_BASE_URL
import com.squareup.moshi.Json

data class Monster (
    val monsterName: String,
    val imageFile: String,
    val caption: String,
    val description: String,
    val price: Double,
    val scariness: Int

) {
    val imageUrl
        get() = "$IMAGE_BASE_URL/$imageFile.webp"

     val thumbnaiUrl
        get() = "$IMAGE_BASE_URL/${imageFile}_tn.webp"


}