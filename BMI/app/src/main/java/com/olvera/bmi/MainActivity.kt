package com.olvera.bmi

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.olvera.bmi.databinding.ActivityMainBinding
import java.lang.Math.pow
import java.math.BigDecimal
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var height: Float = 0f
    private var weight: Float = 0f
    private var value: Float = 0f
    private var advice: String = ""

    private var resultBMIActivity = ResultBMIActivity()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        initialSliders()


        binding.apply {

            sliderHeight.addOnChangeListener { slider, value, fromUser ->
                heightNumText.text = value.toBigDecimal().setScale(2, BigDecimal.ROUND_FLOOR).toString()

            }

            sliderWeight.addOnChangeListener { slider, value, fromUser ->
                weightNumText.text = value.toInt().toString()
            }

            calculateButton.setOnClickListener {
                height = sliderHeight.values[0].toBigDecimal().setScale(2, BigDecimal.ROUND_FLOOR).toFloat()
                weight = sliderWeight.values[0].toBigDecimal().setScale(2, BigDecimal.ROUND_FLOOR).toFloat()
                val intent = Intent(this@MainActivity, ResultBMIActivity::class.java)
                val bmiValue = weight!! / pow(height!!.toDouble(), 2.0)

                if (bmiValue < 18.5) {
                    value = bmiValue.toFloat()
                    advice = getString(R.string.eat_more_pies)
                    resultBMIActivity.bmiValue = value.toString()
                    resultBMIActivity.advice = advice
                } else if (bmiValue > 18.5 && bmiValue <= 24.9) {
                    value = bmiValue.toFloat()
                    advice = getString(R.string.fit_as_fiddle)

                    resultBMIActivity.bmiValue = value.toString()
                    resultBMIActivity.advice = advice
                } else {
                    value = bmiValue.toFloat()
                    advice = getString(R.string.eat_less_pies)

                    resultBMIActivity.bmiValue = value.toString()
                    resultBMIActivity.advice = advice
                }

                intent.putExtra("valueBMI", resultBMIActivity.bmiValue)
                intent.putExtra("advice", resultBMIActivity.advice)
                startActivity(intent)

            }


        }

        formatSlider()
        setContentView(binding.root)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun formatSlider() {
        binding.apply {
            sliderHeight.setLabelFormatter{ value: Float ->
                val format = NumberFormat.getNumberInstance()
                format.maximumFractionDigits = 2
                format.format(value.toDouble())
            }

            sliderWeight.setLabelFormatter{ value: Float ->
                val format = NumberFormat.getNumberInstance()
                format.maximumFractionDigits = 0
                format.format(value.toDouble())
            }
        }
    }

    private fun initialSliders() {
        binding.apply {
            sliderHeight.setValues(1.5f)
            sliderWeight.setValues(100f)

        }
    }

    private fun getValue(): String {
        return value.toString()
    }
}