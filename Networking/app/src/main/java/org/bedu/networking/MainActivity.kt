package org.bedu.networking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import okhttp3.*
import org.bedu.networking.databinding.ActivityMainBinding
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val baseUrl = "https://swapi.dev/api/planets/"

    private lateinit var adapter: PlanetAdapter
    var planetList = ArrayList<Planet>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRequest.setOnClickListener {
            llamadaAsincrona()
        }

        binding.btnSincrono.setOnClickListener {

            Thread{
                llamadaSincrona()
            }.start()
        }

        adapter = PlanetAdapter(this,planetList)

    }

    private fun llamadaAsincrona() {
        //instanciando al cliente
        val okHttpClient = OkHttpClient()

        //Obteniendo la url completa
        val planetNumber = Random.nextInt(1,61) //son 60 planetas
        val url = "$baseUrl$planetNumber/"

        //El objeto Request contiene todos los parámetros de la petición (headers, url, body etc)
        val request = Request.Builder()
            .url(url)
            .build()

        //Enviando y recibiendo las llamadas de forma asíncrona
        okHttpClient.newCall(request).enqueue(object  : Callback {
            //El callback a ejecutar cuando hubo un error

            override fun onFailure(call: Call, e: IOException) {
                Log.d("Error", e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()

                try {
                    val json = JSONObject(body)
                    val phrare = getString(R.string.choosen_planet)
                    val planet = json.getString("name")
                    Log.d("Response: ", "name: $planet")

                    runOnUiThread{
                        binding.textView.text = "$phrare $planet"
                    }

                }catch (e: JSONException){

                }
            }
        })
    }

    //Nuestro thread se bloquea hasta recuperar la información
    fun llamadaSincrona(){

        val client = OkHttpClient()

        //obteniendo la url completa
        val planetNumber = Random.nextInt(1,61) //son 61 planetas
        val url = "$baseUrl$planetNumber/"

        val request =  Request.Builder()
            .url(url)
            .build()

        try {
            val response = client.newCall(request).execute()
            val body = response.body?.string()
            Log.d("Response: ", body.toString())

            val json = JSONObject(body)
            val phrase = getString(R.string.choosen_planet)
            val planet = json.getString("name")
            runOnUiThread{
                binding.textView.text ="$phrase $planet"
            }
        } catch (e: Error){
            Log.e("Error",e.toString())
        }
    }

    fun populateAdapter(data: ArrayList<Planet>){
        planetList.clear()
        planetList.addAll(data)
        adapter.notifyDataSetChanged()
    }

}