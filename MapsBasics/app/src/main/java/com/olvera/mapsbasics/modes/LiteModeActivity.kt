package com.olvera.mapsbasics.modes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.olvera.mapsbasics.R
import com.olvera.mapsbasics.common.Locations.malaga
import com.olvera.mapsbasics.databinding.ActivityFirstMapBinding
import com.olvera.mapsbasics.databinding.ActivityLiteModeBinding

class LiteModeActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityLiteModeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLiteModeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.addMarker(MarkerOptions().position(malaga).title("Malaga"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(malaga))

        googleMap.uiSettings.apply {
            //isZoomGesturesEnabled = true
            //isZoomControlsEnabled = true
            isMapToolbarEnabled = false
        }
    }
}