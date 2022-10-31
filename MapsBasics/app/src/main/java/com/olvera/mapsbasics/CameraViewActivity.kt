package com.olvera.mapsbasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.MarkerOptions
import com.olvera.mapsbasics.databinding.ActivityFirstMapBinding

class CameraViewActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityFirstMapBinding
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirstMapBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        //map.addMarker(MarkerOptions().position(Locations.valencia).title("Programs"))
        //map.moveCamera(CameraUpdateFactory.newLatLng(Locations.valencia))
        val reformaCamera = CameraPosition.Builder()
            .target(Locations.reforma)
            .bearing(60f)
            .tilt(45f)
            .build()

        map.moveCamera(CameraUpdateFactory.newCameraPosition(reformaCamera))
    }
}