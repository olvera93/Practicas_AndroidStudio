package com.olvera.mapsbasics

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CustomCap
import com.google.android.gms.maps.model.Dash
import com.google.android.gms.maps.model.Dot
import com.google.android.gms.maps.model.Gap
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.olvera.mapsbasics.databinding.ActivityFirstMapBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PolylineActivity : AppCompatActivity(), OnMapReadyCallback {

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

        map.uiSettings.isZoomControlsEnabled = true

        runPolyline()
        //runPolygon()
        //runCircle()

    }

    private fun runPolyline() {
        val route = mutableListOf(Locations.reforma, Locations.medillin, Locations.malaga, Locations.valencia)
        map.addMarker(MarkerOptions().position(Locations.valencia).title("Valencia, Spain"))

        val polyline = map.addPolyline(PolylineOptions()
            .width(8f)
            .color(Color.BLUE)
            .geodesic(true)
            .clickable(true))

        //polyline.points = route
        lifecycleScope.launch {
            route.add(Locations.medillin)
            route.add(Locations.valenciaVen)
            val runtimeRoute = mutableListOf<LatLng>()
            for (point in route) {
                runtimeRoute.add(point)
                polyline.points = runtimeRoute
                delay(1_500)
            }
        }

        polyline.tag = "From Valencia to Valencia"
        map.setOnPolylineClickListener {
            Toast.makeText(this, "${it.tag}", Toast.LENGTH_SHORT).show()
        }

        polyline.pattern = listOf(Dot(), Gap(16f), Dash(32f), Gap(16f))
        polyline.width = 16f
        polyline.jointType = JointType.ROUND

        //polyline.startCap = RoundCap()
        Utils.getBitmapFromVector(this, R.drawable.ic_directions_run)?.let {
            polyline.startCap = CustomCap(BitmapDescriptorFactory.fromBitmap(it))
        }

        Utils.getBitmapFromVector(this, R.drawable.ic_star)?.let {
            polyline.endCap = CustomCap(BitmapDescriptorFactory.fromBitmap(it))
        }
    }

    private fun runPolygon() {
        TODO("Not yet implemented")
    }

    private fun runCircle() {
        TODO("Not yet implemented")
    }




}