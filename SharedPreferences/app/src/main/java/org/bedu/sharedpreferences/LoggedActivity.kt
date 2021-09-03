package org.bedu.sharedpreferences

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.bedu.sharedpreferences.databinding.ActivityLoggedBinding

class LoggedActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoggedBinding.inflate(layoutInflater) }

    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        preferences = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE)

        setValues()

        binding.btnClose.setOnClickListener {
            resetShared()
            goToLogged()
        }
    }

    private fun goToLogged() {
        val i = Intent(this, MainActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }

    private fun setValues() {
        val email = preferences.getString(MainActivity.EMAIL, "")
        binding.tvEmail.text = email
    }

    private fun resetShared() {
        preferences.edit().clear().commit()
    }
}