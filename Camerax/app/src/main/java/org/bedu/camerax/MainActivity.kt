package org.bedu.camerax

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Camera
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import org.bedu.camerax.databinding.ActivityMainBinding
import android.provider.MediaStore




class MainActivity : AppCompatActivity() {

    companion object{
        val PERMISSION_ID = 34
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOpenCamera.setOnClickListener {
            if (checkCameraPermission()){
                openCamera()
            } else {
                requestPermission()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_ID) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            }
        }
    }

    private fun openCamera(){
        val launchIntent = Intent(this, CameraActivity::class.java)
        launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        launchIntent.putExtra(
            "android.intent.extras.CAMERA_FACING",
            Camera.CameraInfo.CAMERA_FACING_FRONT
        )
        launchIntent.putExtra("android.intent.extras.LENS_FACING_FRONT", 1)
        launchIntent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true)

        startActivity(launchIntent)

    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            PERMISSION_ID
        )

    }

    private fun checkCameraPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED
    }

}