package org.bedu.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.recycler)

        recycler.adapter = RecyclerAdapter(listOf(
            Contact("Javier", "5511223344"),
            Contact("Juan", "5522334455"),
            Contact("Luis", "5533445566"),
            Contact("Fernanda", "5544556677"),
            Contact("Luisa", "5555667788"),
            Contact("Manuel", "5566778899"),
            Contact("Jose", "5577889900"),
            Contact("Maria", "5588990011"),
            Contact("Ana", "5599001122"),
        ))

    }
}