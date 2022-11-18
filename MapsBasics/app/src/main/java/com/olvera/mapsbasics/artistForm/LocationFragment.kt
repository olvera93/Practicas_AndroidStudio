package com.olvera.mapsbasics.artistForm

import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.olvera.mapsbasics.R
import com.olvera.mapsbasics.common.Constants.BIRTH_LOCATION
import com.olvera.mapsbasics.common.Constants.STR_CONCAT
import com.olvera.mapsbasics.common.entities.BirthLocation
import com.olvera.mapsbasics.databinding.FragmentLocationBinding
import java.io.IOException
import java.util.*

class LocationFragment(private val auxLocation: AuxLocation): DialogFragment() {

    private lateinit var centerLocation: LatLng
    private lateinit var map: GoogleMap

    private var _binding: FragmentLocationBinding? = null
    private val binding get() = _binding!!

    private var supportMapFragment: SupportMapFragment? = null
    private var birthLocation: BirthLocation? = null

    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap

        map.uiSettings.apply {
            isZoomControlsEnabled = true
            isRotateGesturesEnabled = false
        }

        birthLocation?.let {
            map.moveCamera(CameraUpdateFactory.newLatLng(it.getLocation()))
            setupCameraIdle()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NORMAL, R.style.FullScreenDialog)
        arguments?.let {
            birthLocation = Gson().fromJson(it.getString(BIRTH_LOCATION), BirthLocation::class.java)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLocationBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        supportMapFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?

        supportMapFragment?.getMapAsync(callback)

        setupToolbar()

        binding.btnDone.setOnClickListener { sendSelectedLocation() }

    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener { dismiss() }
    }

    override fun onDestroy() {
        super.onDestroy()
        supportMapFragment?.let {
            requireActivity().supportFragmentManager.beginTransaction().remove(it).commit()
        }
        _binding = null
    }

    private fun sendSelectedLocation() {
        auxLocation.setBirthLocation(centerLocation.latitude, centerLocation.longitude, binding.tvAddress.text.toString().trim())
        dismiss()
    }

    private fun setupCameraIdle() {
        map.setOnCameraIdleListener {
            centerLocation = map.cameraPosition.target
            // get Address from current location
            getAddressFromLocation(centerLocation.latitude, centerLocation.longitude)
        }
    }

    private fun getAddressFromLocation(latitude: Double, longitude: Double) {
        val geocoder = Geocoder(requireActivity(), Locale.ENGLISH)
        try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)

            if (addresses.isNotEmpty() && addresses[0].countryCode != null) {
                val fetchedAddress = addresses[0]
                val countryCode = fetchedAddress.countryCode
                val adminArea = fetchedAddress.adminArea
                val locality = fetchedAddress.locality

                binding.tvAddress.text = ""
                binding.tvAddress.append(countryCode)
                adminArea?.let {
                    binding.tvAddress.append(STR_CONCAT)
                    binding.tvAddress.append(adminArea)
                }

                locality?.let {
                    binding.tvAddress.append(STR_CONCAT)
                    binding.tvAddress.append(locality)
                }
            } else {
                binding.tvAddress.setText(getString(R.string.dialog_address_invalid))
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    companion object {
        const val TAG = "LocationFragment"
    }

}