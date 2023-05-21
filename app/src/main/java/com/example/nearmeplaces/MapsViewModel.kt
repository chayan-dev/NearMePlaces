package com.example.nearmeplaces

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

  fun getPlaceDetails(placeId: String) = viewModelScope.launch {
    _placeDetails.postValue(Resource.Loading())
    val response = placesRepository.getPlaceById(placeId)
    _placeDetails.postValue(handlePlaceDetailsResponse(response))
  }

  private fun handlePlaceDetailsResponse(response: Response<PlaceByIdResponse>): Resource<PlaceByIdResponse> {
    if (response.isSuccessful) {
      response.body()?.let { tagsResponse ->
        return Resource.Success(tagsResponse)
      }
    }
    return Resource.Error(response.message())
  }


}