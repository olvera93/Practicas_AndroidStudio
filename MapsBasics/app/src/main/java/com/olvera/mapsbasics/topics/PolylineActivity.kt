package com.olvera.mapsbasics.topics

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.olvera.mapsbasics.R
import com.olvera.mapsbasics.common.Locations
import com.olvera.mapsbasics.common.Utils
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
        runPolygon()
        runCircle()

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
        val independencia = LatLng(-11.992576249432506, -77.05270706593035)
        val sanJuan = LatLng(-11.96792744484697, -76.99495251112393)
        val ate = LatLng(-12.014875606395274, -76.88600276805225)
        val santiago = LatLng(-12.14112545046952, -76.9925527370836)
        val villas = LatLng(-12.165289156355678, -76.91935962777876)
        val chorrillos = LatLng(-12.185228423410429, -77.00695138156985)
        val laPunta = LatLng(-12.070263907908664, -77.16389660617087)


        val polygon2 = LatLng(-11.988207626414038, -77.09760443447486)
        val polygon3 = LatLng(-11.994333204346937, -77.08232460741048)
        val polygon4 = LatLng(-12.013199109899176, -77.09434807788736)
        val polygon5 = LatLng(-11.994823244571988, -77.10411714764984)

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(Locations.lima, 10f))

        // Hole
        val musNW = LatLng(-12.063927688644904, -77.07918458143513)
        val musNE = LatLng(-12.062754298504712, -77.06190620808455)
        val musSE = LatLng(-12.076834641118108, -77.06046634363867)
        val musSW = LatLng(-12.076365308206956, -77.08086442328866)
        val musHole = listOf(musNW, musNE, musSE, musSW)

        val polygon = map.addPolygon(PolygonOptions().add(independencia, sanJuan, ate, santiago,villas, chorrillos, laPunta)
            .fillColor(ContextCompat.getColor(this, R.color.fill_polygon))
            .strokeColor(ContextCompat.getColor(this, R.color.stroke_polygon))
            .strokeWidth(4f)
            .geodesic(true)
            .clickable(true)
            .addHole(musHole)
        )

        val task2 = map.addPolygon(PolygonOptions().add(polygon2, polygon3, polygon4, polygon5)
            .fillColor(ContextCompat.getColor(this, R.color.fill_polygon))
            .strokeColor(ContextCompat.getColor(this, R.color.stroke_polygon))
            .strokeWidth(4f)
            .geodesic(true)
            .clickable(true)
        )

        task2.tag = "Task 2"
        map.setOnPolygonClickListener {
            Toast.makeText(this, "Great area to ${it.tag}", Toast.LENGTH_SHORT).show()
        }

        polygon.tag = "Walk"
        map.setOnPolygonClickListener {
            Toast.makeText(this, "Great area to ${it.tag}", Toast.LENGTH_SHORT).show()
        }



    }

    private fun runCircle() {

        val olivos = LatLng(-11.959006370441552, -77.07486498867034)
        val circle = map.addCircle(CircleOptions()
            .center(olivos)
            .radius(2_000.0)
            .fillColor(ContextCompat.getColor(this, R.color.fill_circle))
            .strokeColor(ContextCompat.getColor(this, R.color.stroke_circle))
            .clickable(true)
        )

        circle.tag = "3 Km around"

        map.setOnCircleClickListener {
            Toast.makeText(this, "Denied ${it.tag}", Toast.LENGTH_SHORT).show()
        }

        lifecycleScope.launch {
            delay(3_000)
            circle.isVisible = false
            delay(1_000)
            circle.isVisible = true
            delay(2_000)
            circle.remove()
        }
    }




}