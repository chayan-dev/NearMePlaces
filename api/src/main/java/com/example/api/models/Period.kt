package com.example.api.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Period(
    @Json(name = "close")
    val close: Close,
    @Json(name = "open")
    val `open`: Open
)