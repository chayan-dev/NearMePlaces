package com.example.api.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Open(
    @Json(name = "date")
    val date: String,
    @Json(name = "day")
    val day: Int,
    @Json(name = "time")
    val time: String
)