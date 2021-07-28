package org.bedu.spinner

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
class MainActivity : AppCompatActivity() {
    var language = arrayOf("EUA", "Mexico", "Brasil", "Francia",  "Alemán", "Italia", "Holandés")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var spinner=findViewById<Spinner>(R.id.spinner)
        var text = findViewById<TextView>(R.id.textView)
        var button = findViewById<Button>(R.id.button)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                showDialog("No seleccionaste idioma","Vuelve a desplegar la lista y asegúrate de elegir correctamente a alguna")
                Toast.makeText(applicationContext, "No idioma" , Toast.LENGTH_LONG).show()
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position){
                    0-> {text.text="Select your Country"
                        button.text="Next"
                        Toast.makeText(applicationContext ,language[position] , Toast.LENGTH_LONG).show()
                    }
                    1-> {text.text="Selecciona tu idioma"
                        button.text="Siguiente"
                        Toast.makeText(applicationContext ,language[position] , Toast.LENGTH_LONG).show()
                    }
                    2-> {text.text="Escolha o seu país"
                        button.text="Próximo"
                        Toast.makeText(applicationContext ,language[position] , Toast.LENGTH_LONG).show()
                    }
                    3-> {text.text="Sélectionnez votre pays"
                        button.text="Suivant"
                        Toast.makeText(applicationContext ,language[position] , Toast.LENGTH_LONG).show()
                    }
                    4-> {text.text="Wähle dein Land"
                        button.text="nächste"
                        Toast.makeText(applicationContext ,language[position] , Toast.LENGTH_LONG).show()
                    }
                    5-> {text.text="Seleziona il tuo paese"
                        button.text="prossimo"
                        Toast.makeText(applicationContext ,language[position] , Toast.LENGTH_LONG).show()
                    }
                    else->{
                        text.text="Valige oma riik"
                        button.text="järgmine"
                        Toast.makeText(applicationContext ,language[position] , Toast.LENGTH_LONG).show()
                    }
                }
                Toast.makeText(applicationContext ,language[position] , Toast.LENGTH_LONG).show()
            }
        }
        val languageAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, language)
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = languageAdapter
    }
    private fun showDialog(title:String,message:String){
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK"){dialogInterface, which -> }
            .create()
            .show()
    }
}