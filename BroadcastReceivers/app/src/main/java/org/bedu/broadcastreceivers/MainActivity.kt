package org.bedu.broadcastreceivers

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button

    private val receiverTwo = ReceiverTwo()
    private val airplaneReceiver = AirplaneReceiver()
    private val phoneCallReceiver = PhoneCallReceiver()

    companion object {
        const val NAME = "NAME"
        const val EMAIL = "EMAIL"

        const val ACTION_NAME = "org.bedu.SALUDO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)

        button.setOnClickListener {

            if (!hasPermission()){
                askPermission()
            } else {
                recordCallIfExists()
            }


        }

        //registerAirplane()

    }

    private fun hasPermission(): Boolean{
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_PHONE_STATE ) == PackageManager.PERMISSION_GRANTED
    }

    private fun askPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.READ_PHONE_STATE), 1)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1) {


            if (grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                // Aqui va el código si aceptó el permiso
            } else {
                Toast.makeText(
                    this,
                    "No puedes acceder sin este permiso",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }

    }

    private fun recordCallIfExists(){
        val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        if (telephonyManager.callState == TelephonyManager.CALL_STATE_OFFHOOK) {
            Toast.makeText(this, "Grabando llamada", Toast.LENGTH_SHORT).show()
            registerReceiver(
                phoneCallReceiver,
                IntentFilter("android.intent.action.PHONE_STATE")
            )

        } else {
            Toast.makeText(this, "No estás en ninguna llamada", Toast.LENGTH_SHORT).show()
        }
    }



    override fun onStart() {
        super.onStart()

        IntentFilter().apply {
            addAction(ACTION_NAME)
        }.also { filter -> registerReceiver(receiverTwo,filter) }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiverTwo)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(phoneCallReceiver)
    }

    private fun registerAirplane() {
        IntentFilter().apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }.also { filter -> registerReceiver(airplaneReceiver, filter) }
    }



    private fun emit() {
        val bundle = Bundle().apply {
            putString(NAME, "Rudyard")
            putString(EMAIL, "rudyard@bedu.org")

        }

        /*
        Intent(this, ReceiverOne::class.java).apply {
            putExtras(bundle)
        }.let(::sendBroadcast)

         */

        Intent(ACTION_NAME).apply {
            putExtras(bundle)
        }.let(::sendBroadcast)




    }
}