package com.example.api.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NearbyPlacesResponse(
    @Json(name = "html_attributions")
    val htmlAttributions: List<Any>,
    @Json(name = "next_page_token")
    val nextPageToken: String?,
    @Json(name = "results")
    val results: List<Result>,
    @Json(name = "status")
    val status: String
)