package org.bedu.holabedu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextLastName: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var editTextEmail: EditText

    private lateinit var imageStatus: ImageView
    private lateinit var btnAccept: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageStatus = findViewById(R.id.imageLogin)
        editTextName = findViewById(R.id.txtName)
        editTextLastName = findViewById(R.id.txtLastName)
        editTextPhone = findViewById(R.id.txtPhone)
        editTextEmail = findViewById(R.id.txtEmail)
        btnAccept = findViewById(R.id.btnAccept)

        btnAccept.setOnClickListener{
            if (editTextName.text.toString().isEmpty() || editTextLastName.text.toString().isEmpty() ||
                    editTextPhone.text.toString().isEmpty() || editTextEmail.text.toString().isEmpty()){
                Toast.makeText(this, "FALTAN PARÁMETROS!!", Toast.LENGTH_LONG).show()
                imageStatus.setImageResource(R.drawable.wrong)
            } else {
                Toast.makeText(this, "REGISTRO ÉXITO!!", Toast.LENGTH_LONG).show()
                imageStatus.setImageResource(R.drawable.correct)
            }
        }

        /*text2 = findViewById(R.id.textView2)
        /editText = findViewById(R.id.editText)

        editText.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                text2.text = editText.text
            }
        })

         */

    }
}