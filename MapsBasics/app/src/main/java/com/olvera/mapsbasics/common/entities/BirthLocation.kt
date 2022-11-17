package com.olvera.mapsbasics.common.entities

import com.google.android.gms.maps.model.LatLng
import com.olvera.mapsbasics.common.Constants.STR_CONCAT

data class BirthLocation(
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var placeDetails: String = ""
    ) {

    private fun getCity(): String {
        val places = placeDetails.split(STR_CONCAT).toTypedArray()
        return if(places.size > 2) places[2] else ""
    }

    private fun getState(): String {
        val places = placeDetails.split(STR_CONCAT).toTypedArray()
        return if(places.size > 1) places[1] else ""
    }

    private fun getCountry(): String {
        val places = placeDetails.split(STR_CONCAT).toTypedArray()
        return if(places.size > 0) places[0] else ""
    }

    fun getLocation(): LatLng = LatLng(latitude, longitude)
    fun customFormatDetails(): String = "Country: ${getCountry()}\nState: ${getState()}\nCity: ${getCity()}"
}
