package com.olvera.mapsbasics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.olvera.mapsbasics.databinding.ActivityMainBinding
import com.olvera.mapsbasics.modes.LiteModeActivity
import com.olvera.mapsbasics.modes.SplitStreetViewActivity
import com.olvera.mapsbasics.topics.*

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnFirstMap.setOnClickListener  (this@MainActivity)
            btnNewProcessor.setOnClickListener (this@MainActivity)
            btnPanorama.setOnClickListener (this@MainActivity)
            btnCameraView.setOnClickListener (this@MainActivity)
            btnControlGestures.setOnClickListener (this@MainActivity)
            btnEvents.setOnClickListener (this@MainActivity)
            btnMyLocation.setOnClickListener (this@MainActivity)
            btnMarker.setOnClickListener (this@MainActivity)
            btnShapes.setOnClickListener (this@MainActivity)
            btnGroundOverlay.setOnClickListener (this@MainActivity)
            btnTileOverlay.setOnClickListener (this@MainActivity)
            btnLiteMode.setOnClickListener (this@MainActivity)
            // Apps
            btnLiteList.setOnClickListener (this@MainActivity)
            btnArtistMap.setOnClickListener (this@MainActivity)
            btnFormMap.setOnClickListener (this@MainActivity)
            btnSplitStreetView.setOnClickListener (this@MainActivity)
        }


    }

    override fun onClick(view: View?) {

        view?.let {
            when(it.id) {
                R.id.btnFirstMap -> startActivity(Intent(this, FirstMapActivity::class.java))
                R.id.btnNewProcessor -> startActivity(Intent(this, NewProcessorActivity::class.java))
                R.id.btnPanorama -> startActivity(Intent(this, PanoramaActivity::class.java))
                R.id.btnCameraView -> startActivity(Intent(this, CameraViewActivity::class.java))
                R.id.btnControlGestures -> startActivity(Intent(this, ControlGesturesActivity::class.java))
                R.id.btnEvents -> startActivity(Intent(this, EventsActivity::class.java))
                R.id.btnMyLocation -> startActivity(Intent(this, MyLocationActivity::class.java))
                R.id.btnMarker -> startActivity(Intent(this, MarkerActivity::class.java))
                R.id.btnShapes -> startActivity(Intent(this, PolylineActivity::class.java))
                R.id.btnGroundOverlay -> startActivity(Intent(this, GroundOverlayActivity::class.java))
                R.id.btnTileOverlay -> startActivity(Intent(this, TileOverlayActivity::class.java))
                R.id.btnLiteMode -> startActivity(Intent(this, LiteModeActivity::class.java))
                R.id.btnSplitStreetView -> startActivity(Intent(this, SplitStreetViewActivity::class.java))


                // Apps
                R.id.btnLiteList -> startActivity(Intent(this, FirstMapActivity::class.java))
                R.id.btnArtistMap -> startActivity(Intent(this, FirstMapActivity::class.java))
                R.id.btnFormMap -> startActivity(Intent(this, FirstMapActivity::class.java))
            }
        }

    }
}