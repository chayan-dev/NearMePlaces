package com.example.api.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NearbyPlacesResponse(
    @Json(name = "results")
    val results: List<Result>
)