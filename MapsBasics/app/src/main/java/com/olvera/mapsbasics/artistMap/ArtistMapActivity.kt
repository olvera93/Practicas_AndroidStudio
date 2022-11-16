package com.olvera.mapsbasics.artistMap

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.olvera.mapsbasics.R
import com.olvera.mapsbasics.common.Locations.usa
import com.olvera.mapsbasics.common.Utils
import com.olvera.mapsbasics.common.dataAccess.FakeDatabase
import com.olvera.mapsbasics.common.entities.Artist
import com.olvera.mapsbasics.databinding.ActivityArtistMapBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ArtistMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityArtistMapBinding
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityArtistMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.moveCamera(CameraUpdateFactory.newLatLng(usa))
        map.uiSettings.apply {
            isRotateGesturesEnabled = false
            isMapToolbarEnabled = false
            isZoomControlsEnabled = true
        }
        getArtist()

    }

    private fun getArtist() {
        lifecycleScope.launch {
            setInProgress(true)
            val fakeRemoveData = FakeDatabase.getArtist(this@ArtistMapActivity)
            delay(1_000)
            fakeRemoveData?.let { addArtistToMap(it) }
            setInProgress(false)
        }
    }

    private fun addArtistToMap(artists: List<Artist>) {

        for(artist in artists) {
            val markerOptions = MarkerOptions()
                .position(artist.birthLocation.getLocation())
                .title(artist.getFullName())
                .snippet(artist.birthLocation.placeDetails)

            val glideOptions = RequestOptions()
                .centerCrop()
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
            Glide.with(this)
                .asBitmap()
                .load(artist.photoUrl)
                .apply(glideOptions)
                .into(object : CustomTarget<Bitmap?>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap?>?
                    ) {
                        //markerOptions.icon(BitmapDescriptorFactory.fromBitmap(resource))
                        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(Utils.getResizedBitmap(this@ArtistMapActivity, resource, resources.getDimensionPixelSize(R.dimen.img_map_size))))
                        map.addMarker(markerOptions)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}

                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_star))
                        map.addMarker(markerOptions)
                    }
                })



        }
    }

    private fun setInProgress(enable: Boolean) {
        binding.progressBar.visibility = if (enable) View.VISIBLE else View.GONE
    }
}