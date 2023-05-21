package com.example.api.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentOpeningHours(
    @Json(name = "open_now")
    val openNow: Boolean,
    @Json(name = "periods")
    val periods: List<Period>,
    @Json(name = "weekday_text")
    val weekdayText: List<String>
)