package org.bedu.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.bedu.recyclerview.data.Game


class RecyclerAdapter(val games : List<Game>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.tvTitulo)
        private val category = view.findViewById<TextView>(R.id.tvCategoria)
        private val clasi = view.findViewById<TextView>(R.id.tvClasificacion)
        private val rtn = view.findViewById<RatingBar>(R.id.rbCalificacion)
        private val image = view.findViewById<ImageView>(R.id.imgPortada)

        fun bind(game: Game) {
            name.text = game.name
            category.text = game.category
            clasi.text = game.clasification
            rtn.rating = game.rtn
            image.setImageResource(game.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_videogames, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = games[position]
        holder.bind(game)
    }

    override fun getItemCount(): Int {
        return games.size
    }

}
