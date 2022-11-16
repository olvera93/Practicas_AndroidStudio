package com.olvera.mapsbasics.common.entities

data class Artist(
    val id: String = "",
    val name: String = "",
    val surnames: String = "",
    val photoUrl: String = "",
    val birthLocation: BirthLocation = BirthLocation()
) {
    fun getFullName(): String = "$name $surnames"

}
