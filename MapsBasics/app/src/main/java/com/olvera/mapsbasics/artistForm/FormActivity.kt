package com.olvera.mapsbasics.artistForm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.olvera.mapsbasics.R
import com.olvera.mapsbasics.common.Constants.BIRTH_LOCATION
import com.olvera.mapsbasics.common.dataAccess.FakeDatabase
import com.olvera.mapsbasics.common.entities.Artist
import com.olvera.mapsbasics.common.entities.BirthLocation
import com.olvera.mapsbasics.databinding.ActivityFormBinding

class FormActivity : AppCompatActivity(), AuxLocation {

    private lateinit var binding: ActivityFormBinding
    private lateinit var newLocation: BirthLocation

    private var artist: Artist? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getArtist()?.let {
            artist = it
            setupArtist(it)
        }

        binding.btnEditLocation.setOnClickListener { showMap() }
        binding.btnSave.setOnClickListener { uploadData() }



    }

    private fun setupArtist(artist: Artist) {
        binding.etName.setText(artist.name)
        binding.etSurnames.setText(artist.surnames)
        Glide.with(this)
            .load(artist.photoUrl)
            .circleCrop()
            .into(binding.imgPhoto)
        setBirthLocation(artist.birthLocation.latitude, artist.birthLocation.longitude, artist.birthLocation.placeDetails)

    }

    private fun getArtist():Artist? = FakeDatabase.getArtist(this)

    private fun showMap() {
        artist?.let {
            val dialogFragment = LocationFragment(this)
            var args = Bundle()
            args.putString(BIRTH_LOCATION, Gson().toJson(it.birthLocation))
            dialogFragment.arguments = args
            val transaction = supportFragmentManager.beginTransaction()
            dialogFragment.show(transaction, LocationFragment.TAG)
        }

    }

    private fun uploadData() {
        artist?.let {
            it.name = binding.etName.text.toString()
            it.surnames = binding.etSurnames.text.toString()
            it.birthLocation = newLocation

            binding.tvCloud.setText(it.toString())

        }
    }

    // AuxLocation
    override fun setBirthLocation(latitude: Double, longitude: Double, placeDetails: String) {
        newLocation = BirthLocation()
        newLocation.latitude = latitude
        newLocation.longitude = longitude
        newLocation.placeDetails = placeDetails

        binding.etLocation.setText(placeDetails)
    }


}