package com.example.nearmeplaces

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.nearmeplaces.databinding.BottomSheetLayoutBinding
import com.example.nearmeplaces.utils.Resource
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PlaceBottomSheet: BottomSheetDialogFragment() {

  lateinit var binding: BottomSheetLayoutBinding
  private val viewModel: MapsViewModel by activityViewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = BottomSheetLayoutBinding.inflate(inflater,container,false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewModel.placeDetails.observe(this){ response ->
      when(response){
        is Resource.Success -> {
          response.data?.let { place->
            Log.d("placeDetails", response.data.result.name.toString())
            binding.name.text = place.result.name.toString()
            binding.address.text = place.result.formattedAddress
            binding.rating.text = place.result.rating.toString()
          }
        }
        is Resource.Loading -> {

        }
        else -> {}
      }
    }

    binding.closeIv.setOnClickListener { dismiss() }

  }
}