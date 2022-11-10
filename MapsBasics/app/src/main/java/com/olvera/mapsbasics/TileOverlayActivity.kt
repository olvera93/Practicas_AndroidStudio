package com.olvera.mapsbasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.android.gms.maps.model.TileProvider
import com.google.android.gms.maps.model.UrlTileProvider
import com.olvera.mapsbasics.databinding.ActivityFirstMapBinding
import java.net.URL
import java.util.*

class TileOverlayActivity : AppCompatActivity() , OnMapReadyCallback {

    private lateinit var binding: ActivityFirstMapBinding
    private lateinit var map: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirstMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Utils.binding = binding

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map.moveCamera(CameraUpdateFactory.newLatLng(Locations.reforma))


        insertTileOverlay()
    }

    private fun insertTileOverlay() {
        map.mapType = GoogleMap.MAP_TYPE_NONE
        val tileProvider: TileProvider = object : UrlTileProvider(256, 256) {
            override fun getTileUrl(x: Int, y: Int, zoom: Int): URL? {
                val reverseY = (1 shl zoom) - y - 1
                val currentStr = String.format(Locale.US, MOON_MAP_URL, zoom, x, reverseY)
                var url: URL?
                url = try {
                    URL(currentStr)
                } catch (e: Exception) {
                    throw Exception("Error al cargar los mosaicos...")
                }

                return url
            }

        }

        map.addTileOverlay(TileOverlayOptions()
            .tileProvider(tileProvider)
            //.transparency(0.5f)
        )
    }


    companion object {
        private const val MOON_MAP_URL = "https://mw1.google.com/mw-planetary/lunar/lunarmaps_v1/clem_bw/%d/%d/%d.jpg"
    }
}