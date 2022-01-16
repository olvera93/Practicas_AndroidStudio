package com.olvera.bmi

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.graphics.ColorUtils
import com.olvera.bmi.databinding.ActivityResultBmiactivityBinding
import java.math.BigDecimal

class ResultBMIActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBmiactivityBinding
    lateinit var bmiValue : String
    lateinit var advice: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBmiactivityBinding.inflate(layoutInflater)

        val bmi = intent.extras!!.getString("valueBMI")!!.toBigDecimal().setScale(2, BigDecimal.ROUND_FLOOR).toString()
        val bmiInt = intent.extras!!.getString("valueBMI")!!.toBigDecimal().setScale(2, BigDecimal.ROUND_FLOOR).toInt()
        binding.valuebmiText.text = bmi
        binding.adviceText.text = intent.extras!!.getString("advice")

        binding.recalculate.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.apply {
            if (bmiInt < 18.5) {
                resultBackground.setBackgroundColor(Color.BLUE)
                resultText.setTextColor(Color.WHITE)
                valuebmiText.setTextColor(Color.WHITE)
                adviceText.setTextColor(Color.WHITE)

            } else if (bmiInt > 18.5 && bmiInt <= 24.9) {
                binding.resultBackground.setBackgroundColor(Color.GREEN )
                resultText.setTextColor(Color.WHITE)
                valuebmiText.setTextColor(Color.WHITE)
                adviceText.setTextColor(Color.WHITE)
            } else {
                binding.resultBackground.setBackgroundColor(Color.RED)
                resultText.setTextColor(Color.WHITE)
                valuebmiText.setTextColor(Color.WHITE)
                adviceText.setTextColor(Color.WHITE)

            }
        }

        setContentView(binding.root)
    }


    private fun result() {

    }
}