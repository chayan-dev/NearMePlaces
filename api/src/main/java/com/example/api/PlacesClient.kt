package com.example.api

import com.example.api.services.PlacesAPI
import com.example.api.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object PlacesClient {
  val retrofitBuilder = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())

  val api = retrofitBuilder
    .build()
    .create(PlacesAPI::class.java)
}