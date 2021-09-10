package org.bedu.broadcastreceivers

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button

    private val receiverTwo = ReceiverTwo()

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
            emit()
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