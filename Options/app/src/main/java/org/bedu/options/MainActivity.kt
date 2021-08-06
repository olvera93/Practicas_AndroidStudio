package org.bedu.options

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // Pinta el menu en la pantalla
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        if (item.isChecked) {
            Toast.makeText(this, "Item1 Checked", Toast.LENGTH_LONG).show()
            item.setChecked(false)
        } else {
            Toast.makeText(this, "Item1 Not Checked", Toast.LENGTH_LONG).show()
            item.setChecked(true)
        }

        when (item.itemId) {
            R.id.item1 -> {
                Toast.makeText(this, "Item1", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.item2 -> {
                Toast.makeText(this, "Item2", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.item3 -> {
                Toast.makeText(this, "Item3", Toast.LENGTH_LONG).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }





}
