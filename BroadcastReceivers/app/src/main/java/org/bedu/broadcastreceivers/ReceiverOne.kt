package org.bedu.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ReceiverOne: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val bundle = intent?.extras
        val name = bundle?.getString(MainActivity.NAME)
        val email = bundle?.getString(MainActivity.EMAIL)

        Toast.makeText(context, "$name $email", Toast.LENGTH_SHORT).show()

    }

}