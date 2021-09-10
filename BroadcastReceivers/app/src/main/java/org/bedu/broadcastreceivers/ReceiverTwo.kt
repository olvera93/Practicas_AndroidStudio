package org.bedu.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock.sleep
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReceiverTwo: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        //Toast.makeText(context, "Evento recibido 2", Toast.LENGTH_SHORT).show()
        ToastCoroutine(intent?.extras, context!!).run {
            execute()
        }
    }

    private inner class ToastCoroutine(
        private val bundle: Bundle?,
        private val context: Context
        ): ViewModel() {

        fun execute() = viewModelScope.launch {
            doInBackground()
            onPostExecute()
        }

            private suspend fun doInBackground(): String = withContext(Dispatchers.IO) {
                val name = bundle?.getString(MainActivity.NAME)
                val email = bundle?.getString(MainActivity.EMAIL)

                Log.d("Background", "$name $email ${Thread.currentThread()}")
                //delay(1000)
                return@withContext "Resultado"

            }

        private fun onPostExecute() {
            val name = bundle?.getString(MainActivity.NAME)
            val email = bundle?.getString(MainActivity.EMAIL)

            Toast.makeText(context, "$name $email", Toast.LENGTH_SHORT).show()
        }


    }
}