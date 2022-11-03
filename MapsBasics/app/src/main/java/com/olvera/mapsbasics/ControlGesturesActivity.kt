package com.olvera.mapsbasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.olvera.mapsbasics.Utils.dp
import com.olvera.mapsbasics.databinding.ActivityFirstMapBinding

class ControlGesturesActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityFirstMapBinding
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirstMapBinding.inflate(layoutInflater)
        Utils.binding = binding

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setContentView(binding.root)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map.addMarker(MarkerOptions().position(Locations.medillin).title("Android"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(Locations.medillin, 12f))

        map.uiSettings.apply {
            //isMyLocationButtonEnabled = true
            isZoomControlsEnabled = true
            isCompassEnabled = false
            isMapToolbarEnabled = true
            isRotateGesturesEnabled = false
            isZoomGesturesEnabled = false
        }

        map.setPadding(0, 0, 0, dp(64))

    }
}