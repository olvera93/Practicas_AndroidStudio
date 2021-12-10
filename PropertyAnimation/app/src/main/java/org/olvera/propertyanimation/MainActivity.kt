package org.olvera.propertyanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.olvera.propertyanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.apply {

            rotateButton.setOnClickListener {
                rotater()
            }

            translateButton.setOnClickListener {
                translater()
            }

            scaleButton.setOnClickListener {
                scaler()
            }

            fadeButton.setOnClickListener {
                fader()
            }

            colorizeButton.setOnClickListener {
                colorizer()
            }

            showerButton.setOnClickListener {
                shower()
            }

        }
        setContentView(binding.root)
    }

    private fun rotater() {
    }

    private fun translater() {
    }

    private fun scaler() {
    }

    private fun fader() {
    }

    private fun colorizer() {
    }

    private fun shower() {
    }
}