package com.bedu.librerias

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bedu.librerias.databinding.ActivityToastyBinding
import es.dmoral.toasty.Toasty

class ToastyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityToastyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityToastyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnError.setOnClickListener {
            Toasty.error(this, "This is an error toast", Toasty.LENGTH_SHORT, true).show()
        }

        binding.btnSuccess.setOnClickListener {
            Toasty.success(this, "Success", Toasty.LENGTH_SHORT, true).show()
        }

        binding.btnInfo.setOnClickListener {
            Toasty.info(this, "Info", Toasty.LENGTH_SHORT, true).show()
        }


        binding.btnInfoFormatted.setOnClickListener {
            Toasty.info(this, "info-formatted", Toast.LENGTH_SHORT, true).show()
        }

        binding.btnWarning.setOnClickListener {
            Toasty.warning(this, "Warning", Toast.LENGTH_SHORT, true).show()
        }

        binding.btnNormal




    }
}