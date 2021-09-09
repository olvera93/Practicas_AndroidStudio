package org.bedu.repaso

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.bedu.repaso.data.Contact

class RecyclerAdapter(val contacts: List<Contact>):
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]

        holder.bind(contact)
    }

    override fun getItemCount(): Int = contacts.size




    class ViewHolder(val view:View) : RecyclerView.ViewHolder(view) {

        private val name = view.findViewById<TextView>(R.id.tvNombre)
        private val phone = view.findViewById<TextView>(R.id.tvPhone)

        fun bind(contact: Contact){
            name.text = contact.name
            phone.text = contact.phone
        }
    }

}

private fun LayoutInflater.inflate(itemData: Int, b: Boolean): View {

}
