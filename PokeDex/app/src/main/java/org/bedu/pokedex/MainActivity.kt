package org.bedu.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.picasso.Picasso
import org.bedu.pokedex.databinding.ActivityMainBinding
import org.bedu.pokedex.model.Pokemon
import retrofit.WebServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val baseUrl = "https://pokeapi.co/api/v2/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {
            searchPokemon()
        }
    }

    private fun searchPokemon(){

        //Contrucción de la instancia de retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //Obteniendo la interfaz donde se define la API
        val endPoint = retrofit.create(WebServices::class.java)

        //Obtener el valor insertado en el editText (nombre del pokemon), y enviarla al endpoint
        val pokeSearch = binding.etPokemon.text.toString()
        val call = endPoint.getPokemon(pokeSearch)

        //Poniendo en la cola llamada FGET
        call?.enqueue(object : Callback<Pokemon> {
            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                Log.e("Error: ", "Error: $t")
            }

            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if (response.code() == 200){
                    val body = response.body()
                    Log.e("Respuesta", "${response.body().toString()}")

                    binding.tvPokemon.text = body?.name
                    binding.tvWeight.text = "peso: " + body?.weight.toString()
                    Picasso.get().load(body?.sprites?.photoUrl).into(binding.pokemon); //esto es lobrd
                } else {
                    Log.e("Not200", "Error not 200: $response")
                }
            }

        })
    }
}