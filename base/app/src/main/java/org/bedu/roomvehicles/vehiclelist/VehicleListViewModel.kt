package org.bedu.roomvehicles.vehiclelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.bedu.roomvehicles.data.local.Vehicle
import org.bedu.roomvehicles.data.local.VehicleRepository
import java.util.*


class VehicleListViewModel(private val vehicleRepository: VehicleRepository): ViewModel() {

    private var _editVehicleId = MutableLiveData<Int?>()
    val eventEditVehicle = _editVehicleId

    var vehicles = vehicleRepository.getVehicles()

    fun getVehicleList(): List<Vehicle> {
        vehicles = vehicleRepository.getVehicles()
        return vehicles
    }

    fun removeVehicle(vehicle: Vehicle) = viewModelScope.launch {
        vehicleRepository.removeVehicle(vehicle)
    }

    fun onEdit(vehicleId: Int) {
        eventEditVehicle.value = vehicleId
    }

    fun prepopulate() {
        val vehicles = listOf(
            Vehicle(
                model = "Vento",
                brand = "Volkswagen",
                platesNumber = "STF0321",
                isWorking = true
            ),
            Vehicle(
                model = "Jetta",
                brand = "Volkswagen",
                platesNumber = "FBN6745",
                isWorking = true
            )
        )

        viewModelScope.launch {
            vehicleRepository.populateVehicles(vehicles)
        }


    }
}