package com.olvera.mapsbasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback
import com.google.android.gms.maps.StreetViewPanorama
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.StreetViewSource

class PanoramaActivity : AppCompatActivity(), OnStreetViewPanoramaReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panorama)

        val panoramaFragment = supportFragmentManager
            .findFragmentById(R.id.streetViewPanorama) as SupportStreetViewPanoramaFragment

        panoramaFragment.getStreetViewPanoramaAsync(this)

    }

    override fun onStreetViewPanoramaReady(streetViewPanorama: StreetViewPanorama) {
        val malaga = LatLng(36.72029468844457, -4.421817632178133)
        streetViewPanorama.apply {
            setPosition(malaga, 50, StreetViewSource.OUTDOOR)

            isPanningGesturesEnabled = false
            isUserNavigationEnabled = false
            isZoomGesturesEnabled = false
            isStreetNamesEnabled = true
        }
    }
}