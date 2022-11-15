package com.olvera.mapsbasics.topics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.olvera.mapsbasics.R

class NewProcessorActivity : AppCompatActivity(), OnMapReadyCallback, OnMapsSdkInitializedCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_map)


        MapsInitializer.initialize(this, MapsInitializer.Renderer.LATEST, this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val guatemala = LatLng(15.958145607922996, -90.13697193327926)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(guatemala, 15f))
        googleMap.addMarker(MarkerOptions().position(guatemala).title("Example"))

        googleMap.isTrafficEnabled = true
    }

    override fun onMapsSdkInitialized(p0: MapsInitializer.Renderer) {

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
}