package org.bedu.roomvehicles.utils

import org.bedu.roomvehicles.data.local.Vehicle
import org.junit.Assert.*
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class VehicleUtilsKtTest {

    @Test
    fun getNumberOfVehicles_empty_returnsZero() {
        //GIVEN: Ddo una lista de vehículos vacia
        val vehicles = listOf<Vehicle>()

        //WHEN
        val result = getNumberOfVehicles(vehicles)

        // THEN
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun getNumberOfVehicles_null_returnsZero() {
        // GIVEN
        val vehicles = null

        // WHEN
        val result = getNumberOfVehicles(vehicles)

        // THEN
        assertThat(result).isEqualTo(0)

    }

    @Test
    fun getNumberOfVehicles_two_returnsTwo() {

        val vehicles = listOf(
            Vehicle(
                0,
                "pointer",
                "VolksWagen",
                "STEWQF123",
                true
            ),
            Vehicle(
                1,
                "Focus",
                "Ford",
                "ASFT454",
                true
            )

        )

        // THEN
        val result = getNumberOfVehicles(vehicles)

        // THEN
        assertThat(result).isEqualTo(2)

    }

    @Test
    fun activeVehiclesPercentage_empty_returnsZero() {
        //GIVEN:
        val vehicles = listOf<Vehicle>()

        //WHEN
        val result = activeVehiclesPercentage(vehicles)

        // THEN
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun activeVehiclesPercentage_null_returnsZero() {
        // GIVEN
        val vehicles = null

        // WHEN
        val result = activeVehiclesPercentage(vehicles)

        // THEN
        assertThat(result).isEqualTo(0)

    }

    @Test
    fun activeVehiclesPercentage_two_returnsTwo() {

        val vehicles = listOf(
            Vehicle(
                0,
                "pointer",
                "VolksWagen",
                "STEWQF123",
                true
            ),
            Vehicle(
                1,
                "Focus",
                "Ford",
                "ASFT454",
                false
            )

        )

        // THEN
        val result = activeVehiclesPercentage(vehicles)

        // THEN
        assertThat(result).isEqualTo(50f)

    }

}