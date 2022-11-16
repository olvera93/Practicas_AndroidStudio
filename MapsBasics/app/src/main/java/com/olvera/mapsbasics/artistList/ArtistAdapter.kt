package com.olvera.mapsbasics.artistList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.olvera.mapsbasics.R
import com.olvera.mapsbasics.common.entities.Artist
import com.olvera.mapsbasics.databinding.ItemLiteListBinding

class ArtistAdapter: ListAdapter<Artist, RecyclerView.ViewHolder>(ArtistDiffCallback()) {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_lite_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val artist = getItem(position)
        with(holder as ViewHolder) {
            location = artist.birthLocation.getLocation()
            binding.itemMap.tag = this
            binding.itemName.text = artist.getFullName()
            Glide.with(context)
                .load(artist.photoUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .circleCrop()
                .into(binding.itemPhoto)

            setupMapLocation()
        }
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view), OnMapReadyCallback {
        val binding = ItemLiteListBinding.bind(view)

        lateinit var map: GoogleMap
        lateinit var location: LatLng

        init {
            with(binding.itemMap) {
                onCreate(null)
                getMapAsync(this@ViewHolder)
            }
        }

        override fun onMapReady(googleMap: GoogleMap) {
            MapsInitializer.initialize(context)
            map = googleMap
            setupMapLocation()
        }

         fun setupMapLocation() {
            if(!::map.isInitialized) return
            map.run {
                moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10f))
                addMarker(MarkerOptions().position(location))
                mapType = GoogleMap.MAP_TYPE_NORMAL
            }
        }

        fun clearMap() {
            map.run {
                clear()
                mapType = GoogleMap.MAP_TYPE_NONE
            }
        }

    }


    class ArtistDiffCallback: DiffUtil.ItemCallback<Artist>() {
        override fun areItemsTheSame(oldItem: Artist, newItem: Artist) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist) = oldItem == newItem

    }


}