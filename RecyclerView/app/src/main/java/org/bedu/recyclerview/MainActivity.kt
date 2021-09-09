package org.bedu.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import org.bedu.recyclerview.data.Game

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.recyclerGame)

        recycler.adapter = RecyclerAdapter(listOf(
            Game("Devil mary cry 5", "Teen", "Action Game", 4.0f, R.drawable.devil_may_cry_5),
            Game("Sekiro: shadows dies twice", "Only Adults", "Game of the year", 3.0f, R.drawable.sekiro),
            Game("Super smash bros ultimate", "Everyone", "Aventures", 5.0f, R.drawable.smash),
            ))
    }
}