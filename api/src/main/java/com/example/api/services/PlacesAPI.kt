package com.example.api.services

import com.example.api.models.NearbyPlacesResponse
import com.example.api.models.PlaceByIdResponse
import com.example.api.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesAPI {

  @GET("details/json")
  suspend fun getPlaceById(
    @Query("place_id")
    placeId: String,
    @Query("key")
    key: String = Constants.API_KEY
  ): Response<PlaceByIdResponse>

  @GET("nearbysearch/json")
  suspend fun getNearbyPlacesByType(
    @Query("location")
    location: String,
    @Query("radius")
    radius: Int = 5000,
    @Query("type")
    type: String,
    @Query("key")
    key: String = Constants.API_KEY
  ): Response<NearbyPlacesResponse>
}