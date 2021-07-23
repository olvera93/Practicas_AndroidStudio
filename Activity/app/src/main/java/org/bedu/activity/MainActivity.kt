package org.bedu.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

const val USER_NAME = "org.bedu.activity.USER_NAME"

class MainActivity : AppCompatActivity() {

    private lateinit var boton: Button
    private lateinit var input: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        boton = findViewById(R.id.buttonAccept)
        input = findViewById(R.id.editText)

        boton.setOnClickListener{
            val bundle = Bundle()

            bundle.putString(USER_NAME, input.text.toString())

            val intent = Intent(this,  WelcomeActivity::class.java).apply { //apply permite agregar contenido
                putExtras(bundle)
            }

            startActivity(intent)
        }

    }
}