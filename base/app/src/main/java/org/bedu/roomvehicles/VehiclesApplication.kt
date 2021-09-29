package org.bedu.roomvehicles

import android.app.Application
import org.bedu.roomvehicles.data.local.Vehicle
import org.bedu.roomvehicles.data.local.VehicleRepository
import org.bedu.roomvehicles.room.VehicleDb

class VehiclesApplication: Application() {

    val vehicleRepository: VehicleRepository
    get() = VehicleRepository(
        VehicleDb.getInstance(this)!!.vehicleDao()
    )
}