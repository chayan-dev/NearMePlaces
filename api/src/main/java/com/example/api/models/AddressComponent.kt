package com.example.api.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddressComponent(
    @Json(name = "long_name")
    val longName: String,
    @Json(name = "short_name")
    val shortName: String,
    @Json(name = "types")
    val types: List<String>
)