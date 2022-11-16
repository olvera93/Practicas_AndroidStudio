package com.olvera.mapsbasics.artistList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecyclerListener
import com.olvera.mapsbasics.R
import com.olvera.mapsbasics.common.dataAccess.FakeDatabase
import com.olvera.mapsbasics.databinding.ActivityLiteListBinding

class LiteListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLiteListBinding
    private lateinit var adapter: ArtistAdapter
    private var recyclerViewListener = RecyclerView.RecyclerListener {holder ->
        (holder as ArtistAdapter.ViewHolder).clearMap()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLiteListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        getArtists()


    }

    private fun setupRecyclerView() {
        adapter = ArtistAdapter()
        binding.recyclerView.apply {
            //layoutManager = LinearLayoutManager(this@LiteListActivity)
            layoutManager = GridLayoutManager(this@LiteListActivity, 2)
            adapter = this@LiteListActivity.adapter
            setHasFixedSize(true)
            setRecyclerListener(recyclerViewListener)
        }
    }

    private fun getArtists() {
        FakeDatabase.getArtist(this)?.let { adapter.submitList(it) }
    }
}