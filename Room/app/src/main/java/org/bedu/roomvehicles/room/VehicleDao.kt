package org.bedu.roomvehicles.room

import androidx.room.*

@Dao
interface VehicleDao {

    @Insert
    fun insertVehicle(vehicle: Vehicle)

    @Update
    fun updateVehicle(vehicle: Vehicle)

    @Delete
    fun deleteVehicle(vehicle: Vehicle)

    @Query("DELETE FROM Vehicle WHERE id=:id")
    fun removeVehicleById(id: Int)

    @Delete
    fun removeVehicles(vararg vehicles: Vehicle) // Argumentos

    @Query("SELECT * FROM Vehicle")
    fun getVehicles(): List<Vehicle>

    @Query("SELECT * FROM Vehicle WHERE brand = :brand")
    fun getVehiclesByBrand(brand: String): List<Vehicle>

    @Query("SELECT * FROM Vehicle WHERE id = :id")
    fun getVehicleById(id: Int): Vehicle

}