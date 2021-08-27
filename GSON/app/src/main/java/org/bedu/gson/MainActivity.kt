package org.bedu.gson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import okhttp3.*
import org.bedu.gson.databinding.ActivityMainBinding
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val url = "https://swapi.dev/api/people/1/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnJedi.setOnClickListener {
            llamarALaFuerza()
        }

        binding.btnSith.setOnClickListener {
            //llamarALaFuerza(true)
        }

    }

    fun llamarALaFuerza(isSith: Boolean = false){

        //instanciando al cliente
        val okHttpClient = OkHttpClient()

        //El objeto Request contiene todos los parámetros de la petición (headers, url, body etc)
        val request = Request.Builder()
            .url(url)
            .build()

        val clientBuilder = okHttpClient.newBuilder()
        clientBuilder.build()
            .newCall(request)
            .enqueue(object : Callback {

                //el callback a ejecutar cuando hubo un error
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("Error",e.toString())
                }

                //el callback a ejectutar cuando obtuvimos una respuesta
                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                    try {
                        val json = JSONObject(body)
                        val name = json.getString("name")
                        val height = json.getString("height")
                        val mass = json.getString("mass")

                        runOnUiThread{
                            binding.tvName.text = name
                            binding.tvHeight.text = height
                            binding.tvWeight.text = mass
                        }

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            })
    }




}