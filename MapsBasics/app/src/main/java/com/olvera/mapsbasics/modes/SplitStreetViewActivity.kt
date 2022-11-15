package com.olvera.mapsbasics.modes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback
import com.google.android.gms.maps.StreetViewPanorama
import com.google.android.gms.maps.StreetViewPanorama.OnStreetViewPanoramaChangeListener
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.StreetViewPanoramaLocation
import com.olvera.mapsbasics.R
import com.olvera.mapsbasics.common.Locations.cancun
import com.olvera.mapsbasics.common.Utils
import com.olvera.mapsbasics.databinding.ActivityLiteModeBinding
import com.olvera.mapsbasics.databinding.ActivitySplitStreetViewBinding

class SplitStreetViewActivity : AppCompatActivity(), OnMapReadyCallback, OnStreetViewPanoramaReadyCallback, OnMarkerDragListener, OnStreetViewPanoramaChangeListener {

    private lateinit var binding: ActivitySplitStreetViewBinding
    private lateinit var streeViewPanorama: StreetViewPanorama
    private var personMarker: Marker? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplitStreetViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val panoramaFragment = supportFragmentManager.findFragmentById(R.id.streetViewPanorama) as SupportStreetViewPanoramaFragment
        panoramaFragment.getStreetViewPanoramaAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.setOnMarkerDragListener(this)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cancun, 16f))
        googleMap.uiSettings.isZoomControlsEnabled = true

        personMarker = googleMap.addMarker(MarkerOptions()
            .position(cancun)
            .draggable(true)
        )

        personMarker?.let { marker ->
            Utils.getBitmapFromVector(this, R.drawable.ic_emoji_people)?.let {
                marker.setIcon(BitmapDescriptorFactory.fromBitmap(it))
            }
        }
    }

    override fun onStreetViewPanoramaReady(panorama: StreetViewPanorama) {
        streeViewPanorama = panorama
        streeViewPanorama.setPosition(cancun)

        streeViewPanorama.setOnStreetViewPanoramaChangeListener(this)
    }

    override fun onMarkerDrag(p0: Marker) {}

    override fun onMarkerDragEnd(marker: Marker) {
        streeViewPanorama.setPosition(marker.position, 2_000)
    }

    override fun onMarkerDragStart(p0: Marker) {}

    override fun onStreetViewPanoramaChange(location: StreetViewPanoramaLocation) {
        personMarker?.position = location.position
    }
}