package org.bedu.axtionmode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.view.ActionMode


class MainActivity : AppCompatActivity() {

    private var actionMode: ActionMode? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textView: TextView = findViewById(R.id.txTextView)


        textView.setOnLongClickListener {
            if (actionMode == null) actionMode = startSupportActionMode(ActionModeCallback())
            true
        }
    }

    inner class ActionModeCallback: ActionMode.Callback {

        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            val inflater = mode?.getMenuInflater()
            inflater?.inflate(R.menu.action_mode, menu)
            mode?.setTitle("Options Menu")
            return true
        }


        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return true
        }


        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            when (item?.getItemId()) {
                R.id.option_1 -> {
                    actionMode?.setTitle("Option1")
                    return true
                }
            }
            return false
        }


        override fun onDestroyActionMode(mode: ActionMode?) {
            actionMode = null
        }


    }

}