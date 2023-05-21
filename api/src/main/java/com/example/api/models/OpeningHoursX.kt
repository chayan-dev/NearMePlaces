package com.example.api.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OpeningHoursX(
    @Json(name = "open_now")
    val openNow: Boolean,
    @Json(name = "periods")
    val periods: List<PeriodX>,
    @Json(name = "weekday_text")
    val weekdayText: List<String>
)