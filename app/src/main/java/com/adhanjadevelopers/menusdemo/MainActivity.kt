package com.adhanjadevelopers.menusdemo

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.view.ActionMode
import androidx.constraintlayout.widget.ConstraintLayout
import android.widget.Toast


class MainActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {
    private lateinit var mActionMode: ActionMode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //textView registered to the context menu
        val textView: TextView = findViewById(R.id.textHappyCoding)
        this.registerForContextMenu(textView)

        //button registered to the action mode menu
        val button = findViewById<Button>(R.id.button)
        button.setOnLongClickListener {
            mActionMode = startSupportActionMode(mActionCallback)!!
            return@setOnLongClickListener true
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }
    }

    //Float contextual menu
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.float_contextual_menu, menu)
    }

    //handling click events in an float context menu items clicks
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.color -> {
                var textView = findViewById<TextView>(R.id.textHappyCoding)
                textView.setTextColor(Color.parseColor("#FF0000"))
                Toast.makeText(this, "color changed", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.font -> {
                var textView = findViewById<TextView>(R.id.textHappyCoding)
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 32F)
                Toast.makeText(this, "size increased", Toast.LENGTH_SHORT).show()

                return true
            }
            else -> {
                return super.onContextItemSelected(item)
            }
        }
    }

    //handling click events in an actionMode contextual menu mode class
    private val mActionCallback = object : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            menuInflater.inflate(R.menu.actionmode_contextual_menu, menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
            return false
        }

        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.name -> {
                    Toast.makeText(this@MainActivity, "Name selected", Toast.LENGTH_SHORT).show()
                    mode.finish()
                    return true
                }
                R.id.description -> {
                    Toast.makeText(this@MainActivity, "This is description", Toast.LENGTH_SHORT)
                        .show()
                    mode.finish()
                    return true
                }
                else -> false
            }
        }

        override fun onDestroyActionMode(mode: ActionMode) {
            //perform any update you will require
        }

    }

    //popup menu
    fun showPopupMenu(view: View) {
        val popup = PopupMenu(this, view)
        popup.setOnMenuItemClickListener(this)
        popup.inflate(R.menu.popup_menu)
        popup.show()

    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.background -> {
                var background = findViewById<ConstraintLayout>(R.id.layoutBackground)
                background.setBackgroundColor(Color.parseColor("#3c3f41"))
                true
            }
            R.id.description -> {
                var textView = findViewById<TextView>(R.id.textHappyCoding)
                textView.text = "From Hello world to changing the world"
                true
            }
            R.id.delete -> {
                var textView = findViewById<TextView>(R.id.textHappyCoding)
                textView.text = ""
                true
            }
            else -> false
        }
    }

    //Checkable menus
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.checkable_menu, menu)

      //Intent based menu
      val  intent= Intent(android.content.Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(android.content.Intent.EXTRA_TEXT,"Hello am intent based menu")

        menu!!.addIntentOptions(
            R.id.intent_based,
            0,
            0,
            this.componentName,
            null,
            intent,
            0,
            null
        )
    return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        item.isChecked = !item.isChecked
        return when (item.itemId) {
            R.id.checkbox -> {
                Toast.makeText(this, "checkable_one", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.checkbox2 -> {
                Toast.makeText(this, "checkable_Two", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.checkbox3 -> {
                Toast.makeText(this, "checkable_Three", Toast.LENGTH_SHORT).show()
                true
            }
            else -> false
        }
    }

}
