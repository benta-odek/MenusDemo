package com.adhanjadevelopers.menusdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }


    //menu options
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menus, menu)
        return true
    }

    //handling click events in an options menu items clicks
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {
                Toast.makeText(this, "This is settings", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.share -> {
                Toast.makeText(this, "This is share", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.history -> {
                Toast.makeText(this, "This is history", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.help -> {
                Toast.makeText(this, "This is is help", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.logout -> {
                Toast.makeText(this, "logged out", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

}