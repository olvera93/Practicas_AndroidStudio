package org.bedu.roomvehicles.vehiclelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.bedu.roomvehicles.data.local.Vehicle
import org.bedu.roomvehicles.databinding.VehicleItemBinding
import java.util.*

class VehicleAdapter(
        val viewModel: VehicleListViewModel
    ) :
    ListAdapter<Vehicle, VehicleAdapter.ViewHolder>(VehicleDiffCallback)  {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(val binding: VehicleItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: VehicleListViewModel, item:Vehicle) {
            binding.apply {
                vehicle = item
                this.viewModel = viewModel
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = VehicleItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        return ViewHolder.from(viewGroup)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = getItem(position)
        viewHolder.bind(viewModel, item)
    }

}

object VehicleDiffCallback: DiffUtil.ItemCallback<Vehicle>() {
    override fun areItemsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
        return oldItem == newItem

    }

}