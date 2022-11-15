package com.olvera.mapsbasics.topics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.olvera.mapsbasics.R
import com.olvera.mapsbasics.common.Locations
import com.olvera.mapsbasics.databinding.ActivityFirstMapBinding

class EventsActivity : AppCompatActivity(), OnMapReadyCallback, OnMarkerClickListener, OnMarkerDragListener {

    private val TAG = "EventsActivity"

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

        val valenciaHill = map.addMarker(MarkerOptions().position(Locations.valenciaHill).title("Hill"))
        var valenciaMaker: Marker? = null
        map.setOnMapClickListener {
            Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show()
            valenciaMaker = map.addMarker(MarkerOptions().position(Locations.valencia).title("Valencia, Spain").zIndex(2f))
            valenciaHill?.zIndex = 1f

        }

        map.setOnMapLongClickListener {
            Toast.makeText(this, "Click largo", Toast.LENGTH_SHORT).show()
            valenciaMaker?.remove()
        }
        valenciaHill?.tag = "Open to walk"
        map.setOnMarkerClickListener(this)
        valenciaHill?.isDraggable = true
        map.setOnMarkerDragListener(this)
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        Log.i(TAG, "onMarkerClick: ${marker.tag}")
        return false

    }

    override fun onMarkerDrag(marker: Marker) {
        Log.i(TAG, "onMarkerDrag: ${marker.position} ....")
    }

    override fun onMarkerDragEnd(marker: Marker) {
        binding.toggleGroup.visibility = View.VISIBLE
    }

    override fun onMarkerDragStart(marker: Marker) {
        binding.toggleGroup.visibility = View.INVISIBLE
    }


}