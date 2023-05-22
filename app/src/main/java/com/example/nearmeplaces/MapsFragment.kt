package com.example.nearmeplaces

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.example.nearmeplaces.databinding.FragmentMapsBinding
import com.example.nearmeplaces.utils.Resource
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.GoogleMap.OnPoiClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsFragment : Fragment(),
  OnMapReadyCallback,
  OnPoiClickListener,
  OnMarkerClickListener {

  private lateinit var binding: FragmentMapsBinding
  private val viewModel: MapsViewModel by activityViewModels()
  private lateinit var mMap: GoogleMap
  private lateinit var lastLocation: Location
  private var filterMarker: Marker? = null
  private lateinit var fusedLocationClient: FusedLocationProviderClient
  private val requestPermissionLauncher =
    registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
      if (isGranted) {
        checkLocationPermission()
      } else {
        Toast.makeText(context, "Location permission not granted", Toast.LENGTH_SHORT).show()
      }
    }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentMapsBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val mapFragment =
      childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment
    mapFragment.getMapAsync(this)

    fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

    viewModel.nearbyPlacesListDetails.observe(viewLifecycleOwner) { response ->
      when (response) {
        is Resource.Success -> {
          hideProgressBar()
          response.data?.let {
            it.results.forEach { result ->
              mMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                  requireContext(),
                  R.raw.style_invisible
                )
              )
              filterMarker = mMap.addMarker(
                MarkerOptions()
                  .position(LatLng(result.geometry.location.lat, result.geometry.location.lng))
                  .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                  .snippet(result.placeId)
              )
            }
          }
        }
        is Resource.Loading -> {
          showProgressBar()
        }
        else -> {
          hideProgressBar()
        }
      }
    }

    binding.bottomNavigation.setOnItemSelectedListener { item ->
      when (item.itemId) {
        R.id.cafe -> {
          onSelectItemAction("restaurant")
          true
        }
        R.id.atm -> {
          onSelectItemAction("atm")
          true
        }
        R.id.hospital -> {
          onSelectItemAction("hospital")
          true
        }
        R.id.school -> {
          onSelectItemAction("school")
          true
        }
        else -> false
      }
    }

    binding.bottomNavigation.setOnItemReselectedListener { item ->
      when (item.itemId) {
        R.id.cafe -> {
          onReselectedItemAction()
        }
        R.id.atm -> {
          onReselectedItemAction()
        }
        R.id.hospital -> {
          onReselectedItemAction()
        }
        R.id.school -> {
          onReselectedItemAction()
        }
        else -> {}
      }
    }
  }

  override fun onMapReady(googleMap: GoogleMap) {
    mMap = googleMap
    checkLocationPermission()
    mMap.setOnPoiClickListener(this)
    mMap.setOnMarkerClickListener(this)
  }

  private fun checkLocationPermission() {
    if (ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.ACCESS_FINE_LOCATION
      ) != PackageManager.PERMISSION_GRANTED
    ) {
      // Permission is not granted, request it
      requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
      return
    }
    getCurrentLocation()
  }

  @SuppressLint("MissingPermission")
  private fun getCurrentLocation() {
    mMap.isMyLocationEnabled = true
    fusedLocationClient.lastLocation.addOnSuccessListener(requireActivity()) { location ->
      if (location != null) {
        lastLocation = location
        val currentLatLng = LatLng(location.latitude, location.longitude)
        placeMarkerOnMap(currentLatLng)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
      }
    }
  }

  private fun placeMarkerOnMap(currentLatLng: LatLng) {
    val markerOptions = MarkerOptions().position(currentLatLng)
    markerOptions.title("$currentLatLng")
    mMap.addMarker(markerOptions)
  }

  private fun onSelectItemAction(type: String) {
    mMap.clear()
    placeMarkerOnMap(LatLng(lastLocation.latitude, lastLocation.longitude))
    viewModel.getNearbyPlacesByType(
      "${lastLocation.latitude},${lastLocation.longitude}",
      type
    )
    Toast.makeText(context, "Showing nearby $type", Toast.LENGTH_LONG).show()
  }

  private fun onReselectedItemAction() {
    mMap.clear()
    placeMarkerOnMap(LatLng(lastLocation.latitude, lastLocation.longitude))
    mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(requireContext(), R.raw.style_visible))
    binding.bottomNavigation.menu.findItem(R.id.extra).isChecked = true
  }

  private fun hideProgressBar() {
    binding.loader.root.visibility = View.INVISIBLE
  }

  private fun showProgressBar() {
    binding.loader.root.visibility = View.VISIBLE
  }

  override fun onPoiClick(poi: PointOfInterest) {
    viewModel.getPlaceDetails(poi.placeId)
    PlaceBottomSheet().show(childFragmentManager, "PlaceBottomSheet")
  }

  override fun onMarkerClick(marker: Marker): Boolean {
    val uu = marker.snippet
    viewModel.getPlaceDetails(marker.snippet.toString())
    PlaceBottomSheet().show(childFragmentManager, "PlaceBottomSheet")
    return false
  }

}