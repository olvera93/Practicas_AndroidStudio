package org.bedu.roomvehicles.vehiclelist

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.bedu.roomvehicles.R
import org.bedu.roomvehicles.VehiclesApplication
import org.bedu.roomvehicles.data.local.Vehicle
import org.bedu.roomvehicles.databinding.FragmentVehicleListBinding
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


/**
 * A fragment representing a list of Items.
 */
class VehicleListFragment : Fragment(){

    private lateinit var vehicleAdapter: VehicleAdapter

    private lateinit var viewModel: VehicleListViewModel

    private lateinit var binding: FragmentVehicleListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun getListener(): ItemListener {
        return this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = VehicleListViewModel(
            (requireContext().applicationContext as VehiclesApplication).vehicleRepository
        )

        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_vehicle_list,
            container,
            false
        )

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.apply {
            buttonAdd.setOnClickListener {
                findNavController().navigate(
                    R.id.action_vehicleListFragment_to_addEditFragment
                )
            }
        }

        setupVehicleList()
        setupEditVehicle()


        return binding.root
    }

    private fun setupEditVehicle() {
        with(viewModel) {
            eventEditVehicle.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                if (eventEditVehicle.value != null) {
                    findNavController().navigate(
                        R.id.action_vehicleListFragment_to_addEditFragment,
                        bundleOf("vehicle_id" to eventEditVehicle.value!!)

                    )
                }
            })
        }


    }

    private fun setupVehicleList() {

        vehicleAdapter = VehicleAdapter(viewModel)

        viewModel.vehicles.observe(viewLifecycleOwner, Observer{
            vehicleAdapter.submitList(it)
        })


            Handler(Looper.getMainLooper()).post {
                vehicleAdapter = VehicleAdapter(viewModel)
                binding.list.apply {
                    adapter = vehicleAdapter
                }

            }


    }

}