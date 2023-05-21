package com.example.nearmeplaces.repository

import com.example.api.PlacesClient

class PlacesRepository {
  private  val api = PlacesClient.api

  suspend fun getPlaceById(id: String) = api.getPlaceById(placeId = id)
}