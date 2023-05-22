package com.example.nearmeplaces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.nearmeplaces.repository.PlacesRepository
import com.example.nearmeplaces.ui.viewmodels.MapsViewModel
import com.example.nearmeplaces.ui.viewmodels.MapsViewModelFactory

class MainActivity : AppCompatActivity() {

  private lateinit var viewModel: MapsViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val placesRepository = PlacesRepository()
    viewModel =
      ViewModelProvider(this, MapsViewModelFactory(placesRepository))
        .get(MapsViewModel::class.java)


  }
}