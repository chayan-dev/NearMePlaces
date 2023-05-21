package com.example.api.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Review(
    @Json(name = "author_name")
    val authorName: String,
    @Json(name = "author_url")
    val authorUrl: String,
    @Json(name = "language")
    val language: String,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "profile_photo_url")
    val profilePhotoUrl: String,
    @Json(name = "rating")
    val rating: Int,
    @Json(name = "relative_time_description")
    val relativeTimeDescription: String,
    @Json(name = "text")
    val text: String,
    @Json(name = "time")
    val time: Int,
    @Json(name = "translated")
    val translated: Boolean
)