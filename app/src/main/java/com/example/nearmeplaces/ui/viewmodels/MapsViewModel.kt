package com.example.nearmeplaces.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.models.NearbyPlacesResponse
import com.example.api.models.PlaceByIdResponse
import com.example.nearmeplaces.repository.PlacesRepository
import com.example.nearmeplaces.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MapsViewModel(
  val placesRepository: PlacesRepository
) : ViewModel(){

  private val _placeDetails: MutableLiveData<Resource<PlaceByIdResponse>> = MutableLiveData()
  val placeDetails: LiveData<Resource<PlaceByIdResponse>> = _placeDetails

  private val _nearbyPlacesListDetails: MutableLiveData<Resource<NearbyPlacesResponse>> = MutableLiveData()
  val nearbyPlacesListDetails: LiveData<Resource<NearbyPlacesResponse>> = _nearbyPlacesListDetails

  fun getPlaceDetails(placeId: String) = viewModelScope.launch {
    _placeDetails.postValue(Resource.Loading())
    val response = placesRepository.getPlaceById(placeId)
    _placeDetails.postValue(handlePlaceDetailsResponse(response))
  }

  fun getNearbyPlacesByType(latLng:String, type: String) = viewModelScope.launch {
    _nearbyPlacesListDetails.postValue(Resource.Loading())
    val response = placesRepository.getNearbyPlacesByType(latLng, type)
    _nearbyPlacesListDetails.postValue(handleNearbyPlacesResponse(response))
  }

  private fun handlePlaceDetailsResponse(response: Response<PlaceByIdResponse>): Resource<PlaceByIdResponse> {
    if (response.isSuccessful) {
      response.body()?.let { placeResponse ->
        return Resource.Success(placeResponse)
      }
    }
    return Resource.Error(response.message())
  }

  private fun handleNearbyPlacesResponse(response: Response<NearbyPlacesResponse>): Resource<NearbyPlacesResponse> {
    if (response.isSuccessful) {
      response.body()?.let { nearbyResponse ->
        return Resource.Success(nearbyResponse)
      }
    }
    return Resource.Error(response.message())
  }


}