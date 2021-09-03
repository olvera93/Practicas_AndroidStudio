package org.bedu.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.bedu.sharedpreferences.databinding.ActivityMainBinding
import java.util.prefs.AbstractPreferences

class MainActivity : AppCompatActivity() {

    companion object{
        val PREFS_NAME = "org.bedu.sharedpreferences"
        val STRING = "STRING"
        val NUMBER = "NUMBER"
        val BOOLEAN = "BOOLEAN"
    }

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE) //Modo privado

        setValues()

        binding.button.setOnClickListener {
            saveValues()

        }
    }

    private fun saveValues() {
        val string = binding.etString.text.toString()
        val number = binding.etNumber.text.toString().toFloat()
        val boolean = binding.switch1.isChecked

        preferences.edit()
            .putString(STRING, string)
            .putBoolean(BOOLEAN, boolean)
            .putFloat(NUMBER, number)
            .apply()
    }

    private fun setValues(){
        val string = preferences.getString(STRING, "")
        val boolean = preferences.getBoolean(BOOLEAN,false)
        val number = preferences.getFloat(NUMBER, 0f)

        with(binding) {
            etString.setText(string)
            switch1.isChecked = boolean
            etNumber.setText(number.toString())
        }


    }
}