package org.bedu.roomvehicles.vehiclelist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import org.bedu.roomvehicles.data.FakeVehicleRepository
import org.bedu.roomvehicles.data.Repository
import org.bedu.roomvehicles.data.local.Vehicle
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import org.bedu.roomvehicles.CoroutineTestRule
import org.junit.Rule

class VehicleListViewModelTest{

    private lateinit var vehicleRepository: Repository
    private lateinit var viewModel: VehicleListViewModel

    private val vento = Vehicle(model="Vento", brand = "Volkswagen", platesNumber = "234343", isWorking = true)
    private val jetta = Vehicle(model="Jetta", brand = "Volkswagen", platesNumber = "544646", isWorking = true)
    private val focus = Vehicle(model="Focus", brand = "Volkswagen", platesNumber = "DFSF43", isWorking = true)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @Before
    fun setup() {
        vehicleRepository = FakeVehicleRepository()

        val vehicles = listOf(vento, jetta)

        vehicleRepository.populateVehicles(vehicles)

        // GIVEN
        viewModel = VehicleListViewModel(vehicleRepository)
    }

    @Test
    fun removeVehicle_removeVehicle(){
        val observer = Observer<List<Vehicle>> {}

        try {
            viewModel.vehicleList.observeForever(observer)

            // WHEN: Cuando probamos agregar un nuevo evento con nuestro ViewModel
            viewModel.removeVehicle(jetta)

            // THEN:  Entonces el evento fue disparado (eso provoca que no sea nulo y que tenga alguno de los estados:
            //  loading, success, error)
            val vehicles = viewModel.vehicleList.value
            assertThat(vehicles).doesNotContain(jetta)
        } finally {
            viewModel.vehicleList.removeObserver(observer) // eliminamos el observer para evitar memory leaks
        }
    }

}