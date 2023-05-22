package com.example.nearmeplaces.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nearmeplaces.repository.PlacesRepository

class MapsViewModelFactory(
  private val placesRepository: PlacesRepository
) : ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return MapsViewModel(placesRepository) as T
  }
}