package com.olvera.mapsbasics.artistList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.olvera.mapsbasics.R
import com.olvera.mapsbasics.common.entities.Artist
import com.olvera.mapsbasics.databinding.ItemLiteListBinding

class ArtistAdapter: ListAdapter<Artist, RecyclerView.ViewHolder>(ArtistDiffCallback()) {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_lite_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val artist = getItem(position)
        with(holder as ViewHolder) {
            binding.itemName.text = artist.getFullName()
            Glide.with(context)
                .load(artist.photoUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .circleCrop()
                .into(binding.itemPhoto)
        }
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemLiteListBinding.bind(view)
    }


    class ArtistDiffCallback: DiffUtil.ItemCallback<Artist>() {
        override fun areItemsTheSame(oldItem: Artist, newItem: Artist) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist) = oldItem == newItem

    }


}