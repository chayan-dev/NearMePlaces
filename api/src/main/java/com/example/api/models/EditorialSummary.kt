package com.example.api.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EditorialSummary(
    @Json(name = "language")
    val language: String,
    @Json(name = "overview")
    val overview: String
)