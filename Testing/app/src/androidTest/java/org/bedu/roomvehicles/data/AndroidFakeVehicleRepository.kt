package org.bedu.roomvehicles.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.bedu.roomvehicles.data.local.Vehicle

class AndroidFakeVehicleRepository: Repository {

    private var observableVehicles = MutableLiveData<List<Vehicle>>()

    override fun getVehicles(): LiveData<List<Vehicle>> {
        return observableVehicles
    }

    override suspend fun removeVehicle(vehicle: Vehicle) {
        val newList: MutableList<Vehicle> = observableVehicles.value?.toMutableList() ?: mutableListOf()
        newList.remove(vehicle)
        observableVehicles.postValue(newList)
    }

    override suspend fun addVehicle(vehicle: Vehicle) {
        val newList: MutableList<Vehicle> = observableVehicles.value?.toMutableList() ?: mutableListOf()
        newList.add(vehicle)
        observableVehicles.postValue(newList)
    }

    override fun populateVehicles(vehicles: List<Vehicle>) {
        observableVehicles.postValue(vehicles)
    }
}