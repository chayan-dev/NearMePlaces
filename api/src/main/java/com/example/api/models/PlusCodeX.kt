package com.example.api.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlusCodeX(
    @Json(name = "compound_code")
    val compoundCode: String,
    @Json(name = "global_code")
    val globalCode: String
)