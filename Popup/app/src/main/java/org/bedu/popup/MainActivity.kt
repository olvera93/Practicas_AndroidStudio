package org.bedu.popup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button: Button = findViewById(R.id.button)
        registerForContextMenu(button)
        button.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        var popMenu = PopupMenu(this, v)
        popMenu.menuInflater.inflate(R.menu.popmenu, popMenu.menu)
        popMenu.setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.item1 -> {
                    Toast.makeText(this, "option 1", Toast.LENGTH_SHORT).show()
                    true
                }
            }
            false
        }
        popMenu.show()
    }




    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        var inflater = menuInflater
        inflater.inflate(R.menu.popmenu, menu)
    }

}