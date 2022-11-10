package com.olvera.mapsbasics

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

object Locations {
    val valencia = LatLng(39.4782267763267, -0.3738793708729851)
    val reforma = LatLng(19.442691514642658, -99.16749720876025)
    val malaga = LatLng(36.72029468844457, -4.421817632178133)
    val medillin = LatLng(6.249077199277291, -75.57011615175826)
    val valenciaHill = LatLng(39.48946560105884, -0.42730433922732014)
    val valenciaVen = LatLng(10.15761768647013, -67.99751129511631)
    val lima = LatLng(-12.047499759534457, -77.04198808324986)

    val malagaDowntownBounds = LatLngBounds(LatLng(36.69859225611359, -4.451033958294272),
        LatLng(36.73732714402777, -4.410436045860529)
    )
}