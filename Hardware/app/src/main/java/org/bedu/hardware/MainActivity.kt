package org.bedu.hardware

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.bedu.hardware.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mFusedLocationProviderClient: FusedLocationProviderClient

    companion object{
        const val PERMISSION_ID = 33
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        binding.btnLocate.setOnClickListener {
            getLocation()
        }

    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        if (checkPermission()) {
            if (isLocationEnabled()){
                mFusedLocationProviderClient.lastLocation.addOnSuccessListener(this) {
                    if (it != null){
                        binding.tvLatitude.text = it?.latitude.toString()
                        binding.tvLongitude.text = it?.longitude.toString()
                    } else {
                        Toast.makeText(this, "Las coordenadas son nulas", Toast.LENGTH_LONG).show()
                    }
                }
            }
        } else{
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_ID
        )
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun checkPermission(): Boolean {
        return checkGranted(Manifest.permission.ACCESS_FINE_LOCATION)
            && checkGranted(Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    private fun checkGranted(permission: String): Boolean {
        return ActivityCompat.checkSelfPermission(this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }
}