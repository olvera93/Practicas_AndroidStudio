package org.bedu.sharedpreferences

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.bedu.sharedpreferences.databinding.ActivityMainBinding
import java.util.prefs.AbstractPreferences

class MainActivity : AppCompatActivity() {

    companion object{
        val PREFS_NAME = "org.bedu.login"
        val EMAIL = "email"
        val IS_LOGGED = "is_logged"
    }

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        preferences = getPreferences(Context.MODE_PRIVATE) //Modo privado


        binding.btnLogin.setOnClickListener {
            saveValues()
            goToLogged()
        }
    }

    override fun onStart() {
        super.onStart()

        if(isLogged()){
            goToLogged()
        }
    }


    private fun saveValues() {
        val email = binding.etMail.text.toString()
        preferences.edit()
            .putString(EMAIL, email)
            .putBoolean(IS_LOGGED, true)
            .apply()
    }

    private fun goToLogged() {
        val i = Intent(this, LoggedActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }

    private fun isLogged():Boolean{
        return preferences.getBoolean(IS_LOGGED, false)
    }
}