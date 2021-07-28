package org.bedu.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(val contacts : List<Contact>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val name = view.findViewById<TextView>(R.id.tvNombre)
        private val phone = view.findViewById<TextView>(R.id.tvPhone)

        fun bind(contact: Contact){
            name.text = contact.name
            phone.text = contact.phone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bind(contact)
    }


    override fun getItemCount(): Int {
        return contacts.size
    }

}

