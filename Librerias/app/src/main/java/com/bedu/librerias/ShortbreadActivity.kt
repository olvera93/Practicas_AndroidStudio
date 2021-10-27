package com.bedu.librerias

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bedu.librerias.databinding.ActivityShortbreadBinding
import com.google.android.material.snackbar.Snackbar
import es.dmoral.toasty.Toasty
import shortbread.Shortcut

@Shortcut(id = "shortBread", icon = R.drawable.ic_info_outline_white_24dp, shortLabel = "ShorBread")
class ShortbreadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShortbreadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShortbreadBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {
            btnSnackBar.setOnClickListener { snackBar() }
            btnDialog.setOnClickListener { dialog() }

        }

    }


    @Shortcut(id = "snackbar", icon = R.drawable.ic_check_white_24dp, shortLabel = "SnackBar")
    fun snackBar() {
        Snackbar.make(binding.content, "SnackBar with shortcut", Snackbar.LENGTH_SHORT).show()
    }

    @Shortcut(id = "dialog", icon = R.drawable.ic_info_outline_white_24dp, shortLabel = "Dialog")
    fun dialog() {
        AlertDialog.Builder(this)
            .setTitle("Dialog with shortcut")
            .setMessage(R.string.shortcut)
            .setNegativeButton("close"){
                dialog, _ ->
                dialog.dismiss()
                Toasty.error(this, "Error", Toast.LENGTH_SHORT, true).show()
            }
            .setPositiveButton("Ok") { dialog, _ ->
                dialog.dismiss()
                Toasty.success(this, "Success", Toast.LENGTH_SHORT, true).show()
            }.create().show()
    }
}