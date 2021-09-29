package org.bedu.roomvehicles.data.local

import android.view.KeyEvent
import kotlinx.coroutines.*
import java.util.*

class VehicleRepository (
    private val vehicleDao: VehicleDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
        ) {


    suspend fun removeVehicle(vehicle: Vehicle) {
        coroutineScope {
            launch { vehicleDao.removeVehicle(vehicle) }
        }
    }

    fun getVehicles(): List<Vehicle> {
        return vehicleDao.getVehicles()
    }

    suspend fun populateVehicles(vehicles: List<Vehicle>) = withContext(ioDispatcher){
        return@withContext vehicleDao.insertAll(vehicles)
    }
}