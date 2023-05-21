package com.example.api.services

import com.example.api.models.PlaceByIdResponse
import com.example.api.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesAPI {

  @GET("place/details/json")
  suspend fun getPlaceById(
    @Query("place_id")
    placeId: String,
    @Query("key")
    key: String = Constants.API_KEY
  ): Response<PlaceByIdResponse>
}