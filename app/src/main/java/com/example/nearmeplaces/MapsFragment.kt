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
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.nearmeplaces.databinding.FragmentMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment(), OnMapReadyCallback {

  private lateinit var binding: FragmentMapsBinding
  private lateinit var mMap: GoogleMap
  private lateinit var lastLocation: Location
  private lateinit var fusedLocationClient: FusedLocationProviderClient
  private val requestPermissionLauncher =
    registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
      if (isGranted) {
        checkLocationPermission()
      } else {
        //TODO: show toast
      }
    }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    binding = FragmentMapsBinding.inflate(inflater,container,false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val mapFragment =
      childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment
    mapFragment.getMapAsync(this)

    fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

    binding.bottomNavigation.setOnItemSelectedListener { item->
      when(item.itemId) {
        R.id.cafe -> {
          Log.d("MapsFrgment","cafe clicked")
          true
          // Respond to navigation item 1 reselection
        }
//        R.id.park -> {
//          true
//          // Respond to navigation item 2 reselection
//        }
        R.id.atm -> {
          true
          // Respond to navigation item 2 reselection
        }
        R.id.hospital -> {
          true
          // Respond to navigation item 2 reselection
        }
        R.id.school -> {
          true
          // Respond to navigation item 2 reselection
        }
        else -> false
      }
    }
  }

  override fun onMapReady(googleMap: GoogleMap) {
    mMap = googleMap
    mMap.uiSettings.isZoomControlsEnabled = true
    checkLocationPermission()
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
    Log.d("MapsFragment","having loc permission")

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

}