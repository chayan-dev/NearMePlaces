package com.example.api.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GeometryX(
    @Json(name = "location")
    val location: Location,
    @Json(name = "viewport")
    val viewport: ViewportX
)