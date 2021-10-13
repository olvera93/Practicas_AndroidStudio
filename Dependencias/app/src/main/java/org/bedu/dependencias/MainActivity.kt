package org.bedu.dependencias

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import org.bedu.dependencias.R
import org.bedu.dependencias.databinding.ActivityMainBinding
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    @Named("String1")
    lateinit var testString: String

    @Inject
    @Named("String2")
    lateinit var testString2: String

    // Punto 1
    @Inject
    @Named("Random")
    lateinit var testRandom: String

    // Punto 2
    @Inject
    @Named("RandomActivity")
    lateinit var testRandom2: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Punto 3
        binding.apply {

            button.setOnClickListener {
                val intent = Intent(applicationContext, OtherActivity::class.java)
                startActivity(intent)
                finish()
            }

        }

        // Punto 4: Se vuelven a generar los números aleatorios cuando se regresa a la pantalla MainActivity

        Log.e("MainActivity", testString)
        Log.e("MainActivity", testString2)
    }

    override fun onResume() {
        super.onResume()
        Log.e("Random", testRandom)

        Log.e("RandomActivity", testRandom2)
    }
}
