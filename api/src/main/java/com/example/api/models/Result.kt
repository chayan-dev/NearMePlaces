package com.example.api.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "geometry")
    val geometry: Geometry,
    @Json(name = "name")
    val name: String,
    @Json(name = "place_id")
    val placeId: String,
    @Json(name = "rating")
    val rating: Double?,
    @Json(name = "formatted_address")
    val formattedAddress: String?,
)