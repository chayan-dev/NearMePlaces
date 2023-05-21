package com.example.api.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CloseX(
    @Json(name = "day")
    val day: Int,
    @Json(name = "time")
    val time: String
)