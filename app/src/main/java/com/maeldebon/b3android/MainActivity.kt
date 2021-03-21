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
        val db = DatabaseHandler(context)

        this.btn_insert.setOnClickListener {
            if(etvEuid.text.toString().isNotEmpty()) {
                val entry = Entry(etvEuid.text.toString().toInt())
                db.insertData(entry)
            } else {
                Toast.makeText(context, "Test", Toast.LENGTH_SHORT).show()
            }
        }

        btn_read.setOnClickListener {
            var data = db.readData()
            tvResult.text = ""
            for (i in 0 until data.size) {
                tvResult.append(data[i].euid.toString())
            }
        }

        btn_delete.setOnClickListener {
            db.deleteData(1)
            btn_read.performClick()
        }
    }
}