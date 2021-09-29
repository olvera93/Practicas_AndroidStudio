package org.bedu.roomvehicles.utils

import android.util.Log
import org.bedu.roomvehicles.data.local.Vehicle



internal fun getNumberOfVehicles(vehicles: List<Vehicle>?): Int{

    return vehicles?.size ?: 0

}

internal fun activeVehiclesPercentage(vehicles: List<Vehicle>?): Float{

    if (vehicles == null || vehicles.isEmpty()) // Si la lista es null o esta vacía
        return 0f

    val activeVehicles = vehicles!!.count{it.isWorking}
    val totalVehicles = vehicles?.size
    return ( (totalVehicles- activeVehicles)/totalVehicles.toFloat() ) * 100f
}