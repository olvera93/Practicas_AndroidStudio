package com.olvera.mapsbasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.olvera.mapsbasics.databinding.ActivityFirstMapBinding

class MarkerActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
    GoogleMap.OnMarkerDragListener {

    private lateinit var binding: ActivityFirstMapBinding
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirstMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toggleGroup.visibility = View.VISIBLE

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(Locations.valenciaHill, 12f))

        val hillMarker = map.addMarker(MarkerOptions().position(Locations.valenciaHill).title("Hill"))

        hillMarker?.run {
            //setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            //setIcon(BitmapDescriptorFactory.defaultMarker(100f))
            Utils.getBitmapFromVector(this@MarkerActivity, R.drawable.ic_terrain_48)?.let {
                setIcon(BitmapDescriptorFactory.fromBitmap(it))
            }
            rotation = 320f
            setAnchor(0.5f, 0.5f)
            isFlat = true
            snippet = "Central"
        }

        map.setOnMarkerClickListener(this)
        hillMarker?.isDraggable = true
        map.setOnMarkerDragListener(this)

        // Custom info window
        map.setInfoWindowAdapter(HillAdapter(this))

    }

    override fun onMarkerClick(marker: Marker): Boolean {
        /*marker.showInfoWindow()
        return true*/
        return false
    }

    override fun onMarkerDrag(marker: Marker) {
        marker.alpha = 0.4f
    }

    override fun onMarkerDragEnd(marker: Marker) {
        binding.toggleGroup.visibility = View.VISIBLE
        marker.title = "New location"
        marker.snippet = "Welcome"
        marker.alpha = 1.0f
    }

    override fun onMarkerDragStart(p0: Marker) {
        binding.toggleGroup.visibility = View.INVISIBLE
    }
}