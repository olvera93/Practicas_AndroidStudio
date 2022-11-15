package com.olvera.mapsbasics.topics.infoWindow

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.olvera.mapsbasics.R
import com.olvera.mapsbasics.databinding.InfoHillBinding

class HillAdapter(context: Context): GoogleMap.InfoWindowAdapter {

    private val binding: InfoHillBinding?

    init {
        val viewRoot = LayoutInflater.from(context).inflate(R.layout.info_hill, null)
        binding = InfoHillBinding.bind(viewRoot)
    }

    override fun getInfoContents(p0: Marker): View? {
        binding?.let {
            it.imgHill.setImageResource(R.drawable.ic_local_florist_48)
            it.rbScore.rating = 4.7f
            it.tvName.text = "Garden Hill"
            it.tvDescription.text = "Test "
        }

        return binding?.root
    }

    override fun getInfoWindow(p0: Marker): View? {
        return null
    }
}