package com.maeldebon.b3android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this

        this.btn_insert.setOnClickListener {
            if(etvEuid.text.toString().isNotEmpty()) {
                val entry = Entry(etvEuid.text.toString().toInt())
                val db = DatabaseHandler(context)
                db.insertData(entry)
            } else {
                Toast.makeText(context, "Test", Toast.LENGTH_SHORT).show()
            }
        }
    }
}