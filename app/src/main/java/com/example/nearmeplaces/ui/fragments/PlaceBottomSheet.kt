package com.example.nearmeplaces.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.nearmeplaces.databinding.BottomSheetLayoutBinding
import com.example.nearmeplaces.ui.viewmodels.MapsViewModel
import com.example.nearmeplaces.utils.Resource
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PlaceBottomSheet : BottomSheetDialogFragment() {

  lateinit var binding: BottomSheetLayoutBinding
  private val viewModel: MapsViewModel by activityViewModels()
  private var latLng: LatLng? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = BottomSheetLayoutBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    //observing point of interest details response from api
    viewModel.placeDetails.observe(this) { response ->
      when (response) {
        is Resource.Success -> {
          hideProgressBar()
          response.data?.let { place ->
            binding.name.text = place.result.name.toString()
            binding.address.text = place.result.formattedAddress
            binding.rating.text = place.result.rating.toString()
            latLng = place.result.geometry.location.let { LatLng(it.lat, it.lng) }
          }
        }
        is Resource.Loading -> {
          showProgressBar()
        }
        else -> {
          hideProgressBar()
          Toast.makeText(context,"api error occurred", Toast.LENGTH_SHORT).show()
        }
      }
    }

    binding.closeIv.setOnClickListener { dismiss() }

    binding.navBtn.setOnClickListener { sendNavigationToGoogleMaps() }
  }

  //intent to google maps app in the device for directions
  private fun sendNavigationToGoogleMaps() {
    val gmmIntentUri =
      Uri.parse("google.navigation:q=${latLng?.latitude},${latLng?.longitude}")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    startActivity(mapIntent)
  }

  private fun hideProgressBar() {
    binding.loader.root.visibility = View.INVISIBLE
  }

  private fun showProgressBar() {
    binding.loader.root.visibility = View.VISIBLE
  }
}