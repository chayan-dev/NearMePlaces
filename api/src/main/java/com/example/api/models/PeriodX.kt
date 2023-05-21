package com.example.api.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PeriodX(
    @Json(name = "close")
    val close: CloseX,
    @Json(name = "open")
    val `open`: OpenX
)