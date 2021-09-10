package org.bedu.roomvehicles

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.bedu.roomvehicles.provider.VehicleProvider
import org.bedu.roomvehicles.room.Vehicle
import org.bedu.roomvehicles.room.VehicleDataBase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


/**
 * A fragment representing a list of Items.
 */
class VehicleListFragment : Fragment(), ItemListener {


    private lateinit var addButton: FloatingActionButton
    private lateinit var recyclerVehicle: RecyclerView
    private lateinit var adapter: VehicleAdapter

    private val vehicleArray = mutableListOf<Vehicle>()

    private val loaderCallbacks: LoaderManager.LoaderCallbacks<Cursor> = object : LoaderManager.LoaderCallbacks<Cursor> {
        override fun onCreateLoader(id: Int, @Nullable args: Bundle?): Loader<Cursor?> {
            Log.d("Vehicles","holamei")
            Log.d("Vehicles","${VehicleProvider.URI_VEHICLE}")
            return CursorLoader(requireContext().applicationContext,
                Uri.parse("${VehicleProvider.URI_VEHICLE}"),
                arrayOf(
                    Vehicle.COLUMN_PK,
                    Vehicle.COLUMN_BRAND,
                    Vehicle.COLUMN_MODEL,
                    Vehicle.COLUMN_PLATES,
                    Vehicle.COLUMN_WORKING
                ),
                null,
                null,
                null
            )
        }

        override fun onLoadFinished(loader: Loader<Cursor?>, data: Cursor?) {

            Log.d("Vehicles","termina, ${data?.count}")

            data?.apply {
                // Determine the column index of the column named "word"
                val pkIndex: Int = getColumnIndex(Vehicle.COLUMN_PK)
                val brandIndex: Int = getColumnIndex(Vehicle.COLUMN_BRAND)
                val modelIndex: Int = getColumnIndex(Vehicle.COLUMN_MODEL)
                val platesIndex: Int = getColumnIndex(Vehicle.COLUMN_PLATES)
                val workingIndex: Int = getColumnIndex(Vehicle.COLUMN_WORKING)

                while (moveToNext()) {
                    // Gets the value from the column.
                    val pk = getInt(pkIndex)
                    val brand = getString(brandIndex)
                    val model = getString(modelIndex)
                    val plates = getString(platesIndex)

                    val vehicle = Vehicle(
                        id = pk,
                        brand = brand,
                        model = model,
                        platesNumber = plates,
                        isWorking = true
                    )


                    vehicleArray.add(vehicle)
                }
            }




            adapter = VehicleAdapter(vehicleArray, getListener())
            recyclerVehicle.adapter = adapter
        }

        override fun onLoaderReset(loader: Loader<Cursor?>) {
            recyclerVehicle.adapter = null
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun getListener(): ItemListener{
        return this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_vehicle_list, container, false)

        addButton = view.findViewById(R.id.button_add)
        recyclerVehicle = view.findViewById(R.id.list)

        addButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_vehicleListFragment_to_addEditFragment
            )
        }


        val executor: ExecutorService = Executors.newSingleThreadExecutor()

        executor.execute(Runnable {
            val vehicleArray = VehicleDataBase
                .getInstance(requireContext())
                ?.vehicleDao()
                ?.getVehicles() as MutableList<Vehicle>

            Handler(Looper.getMainLooper()).post(Runnable {
                adapter = VehicleAdapter(vehicleArray, getListener())
                recyclerVehicle.adapter = adapter
            })
        })

        return view
    }

    override fun onEdit(vehicle: Vehicle) {


    }

    override fun onDelete(vehicle: Vehicle) {

        val executor: ExecutorService = Executors.newSingleThreadExecutor()

        executor.execute {
            VehicleDataBase
                .getInstance(requireContext())
                ?.vehicleDao()
                ?.removeVehicleById(vehicle.id)

            Handler(Looper.getMainLooper()).post {
                adapter.removeItem(vehicle)
                Toast.makeText(requireContext(), "Elemento eliminado", Toast.LENGTH_SHORT).show()
            }
        }

    }

}