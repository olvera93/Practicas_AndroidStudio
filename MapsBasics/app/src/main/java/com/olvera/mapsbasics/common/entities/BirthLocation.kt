package com.olvera.mapsbasics.common.entities

import com.google.android.gms.maps.model.LatLng

data class BirthLocation(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    var placeDetails: String = ""
    ) {

    fun getLocation(): LatLng = LatLng(latitude, longitude)
}
