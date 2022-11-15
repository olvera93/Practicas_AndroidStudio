package com.olvera.mapsbasics.topics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.olvera.mapsbasics.R
import com.olvera.mapsbasics.common.Utils.dp
import com.olvera.mapsbasics.common.Locations
import com.olvera.mapsbasics.common.Utils
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
        setupToggle()

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

        if(isMapStyleReady()) {
            Toast.makeText(this, "¿Listo para navegar?", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isMapStyleReady(): Boolean {
        try {
            return map.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.new_style))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return false
    }

    private fun setupToggle() {
        binding.toggleGroup.visibility = View.VISIBLE
        binding.toggleGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                map.mapType = when(checkedId) {
                    R.id.btnNormal -> GoogleMap.MAP_TYPE_NORMAL
                    R.id.btnHybrid -> GoogleMap.MAP_TYPE_HYBRID
                    R.id.btnSatellite -> GoogleMap.MAP_TYPE_SATELLITE
                    R.id.btnTerrain -> GoogleMap.MAP_TYPE_TERRAIN
                    else -> GoogleMap.MAP_TYPE_NONE
                }
            }
        }
    }
}