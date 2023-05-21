package com.example.api.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ViewportX(
    @Json(name = "northeast")
    val northeast: NortheastX,
    @Json(name = "southwest")
    val southwest: SouthwestX
)