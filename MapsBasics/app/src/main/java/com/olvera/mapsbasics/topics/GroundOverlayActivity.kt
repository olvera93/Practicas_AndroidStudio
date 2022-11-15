package com.olvera.mapsbasics.topics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.GroundOverlayOptions
import com.olvera.mapsbasics.R
import com.olvera.mapsbasics.common.Utils.dp
import com.olvera.mapsbasics.common.Locations
import com.olvera.mapsbasics.common.Utils
import com.olvera.mapsbasics.databinding.ActivityFirstMapBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GroundOverlayActivity : AppCompatActivity(), OnMapReadyCallback {

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

        map.uiSettings.isZoomControlsEnabled = true
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(Locations.lima, 10f))

        insertGroundOverlay()

    }

    private fun insertGroundOverlay() {
        /*Utils.getBitmapFromVector(this, R.drawable.ic_terrain_48)?.let {
            map.addGroundOverlay(GroundOverlayOptions()
                .position(Locations.lima, 1_000f, 1_000f)
                .image(BitmapDescriptorFactory.fromBitmap(it))
            )

        }*/

        /*map.addGroundOverlay(GroundOverlayOptions().position(Locations.lima, 3_000f, 3_000f)
            .image(BitmapDescriptorFactory.fromResource(R.drawable.img_cursos_android_ant))
            .transparency(0.65f)
            .anchor(0.0f, 0.5f) // 0,0=SE  1,1=NE
        )*/
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(Locations.malaga, 10f))

        lifecycleScope.launch {
            delay(2_500)

            val overlay = map.addGroundOverlay(GroundOverlayOptions()
                .positionFromBounds(Locations.malagaDowntownBounds)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.img_cursos_android_ant))
                .transparency(0.65f)
                //.anchor(0.0f, 0.5f) // 0,0=SE  1,1=NE
                .clickable(true)
            )

            overlay?.tag = "Cursos Android"

            map.setOnGroundOverlayClickListener {
                Toast.makeText(this@GroundOverlayActivity, "Zone: ${it.tag}", Toast.LENGTH_SHORT).show()
            }
            map.animateCamera(CameraUpdateFactory.newLatLngBounds(
                Locations.malagaDowntownBounds, dp(300), dp(100), dp(0)
            ), object: GoogleMap.CancelableCallback{
                override fun onCancel() {
                    Toast.makeText(this@GroundOverlayActivity, "CANCEL", Toast.LENGTH_SHORT).show()
                }

                override fun onFinish() {
                    map.animateCamera(CameraUpdateFactory.zoomTo(13f))
                }

            })
        }
    }
}