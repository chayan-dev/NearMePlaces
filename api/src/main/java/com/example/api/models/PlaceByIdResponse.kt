package com.example.api.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlaceByIdResponse(
    @Json(name = "html_attributions")
    val htmlAttributions: List<Any>,
    @Json(name = "result")
    val result: ResultX,
    @Json(name = "status")
    val status: String
)