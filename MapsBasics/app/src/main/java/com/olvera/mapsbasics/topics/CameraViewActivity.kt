package com.olvera.mapsbasics.topics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.olvera.mapsbasics.R
import com.olvera.mapsbasics.common.Locations
import com.olvera.mapsbasics.common.Utils
import com.olvera.mapsbasics.databinding.ActivityFirstMapBinding

class CameraViewActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityFirstMapBinding
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirstMapBinding.inflate(layoutInflater)

        setContentView(binding.root)

        Utils.binding = binding

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        //map.addMarker(MarkerOptions().position(Locations.valencia).title("Programs"))
        //map.moveCamera(CameraUpdateFactory.newLatLng(Locations.valencia))
        /*val reformaCamera = CameraPosition.Builder()
            .target(Locations.reforma)
            .bearing(245f)
            .tilt(40f)
            .zoom(16f)
            .build()

        map.moveCamera(CameraUpdateFactory.newCameraPosition(reformaCamera))
        map.apply {
            setMinZoomPreference(16f)
            setMaxZoomPreference(20f)
        }*/

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(Locations.valencia, 10f))


        /*lifecycleScope.launch {
            delay(5_000)
            map.addMarker(MarkerOptions().position(Locations.malaga))
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(Locations.malaga, 10f))
        }*/

        /*lifecycleScope.launch {
            delay(2_500)
            //map.moveCamera(CameraUpdateFactory.zoomBy(2f))
            map.moveCamera(CameraUpdateFactory.zoomIn())
            delay(2_500)
            map.moveCamera(CameraUpdateFactory.zoomOut())
            delay(3_500)
            map.animateCamera(CameraUpdateFactory.zoomTo(16f))
        }*/

        /*val reformaCamera = CameraPosition.Builder()
            .target(Locations.reforma)
            .bearing(245f)
            .tilt(40f)
            .zoom(16f)
            .build()

        map.animateCamera(CameraUpdateFactory.newCameraPosition(reformaCamera))

        lifecycleScope.launch {
            delay(2_500)
            for (i in 1..15) {
                map.animateCamera(CameraUpdateFactory.scrollBy(0f, -150f))
                delay(500)
            }*/
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(Locations.malaga, 10f))

        /*lifecycleScope.launch {
            delay(2_000)
            map.animateCamera(CameraUpdateFactory.newLatLngBounds(malagaDowntownBounds, dp(32)))
        }*/

        //map.animateCamera(CameraUpdateFactory.newLatLngBounds(malagaDowntownBounds, dp(100), dp(100), dp(32)))
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(Locations.malagaDowntownBounds.center, 13.5f))
        map.setLatLngBoundsForCameraTarget(Locations.malagaDowntownBounds)


    }
}