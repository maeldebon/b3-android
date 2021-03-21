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
                val entry = Entry(
                    etvEuid.text.toString().toInt(),
                    "Name", // TODO replace by real vehicle name
                    "Image", // TODO replace by real vehicle image
                    9, // TODO replace by real vehicle prixjournalierbase
                    "G" // TODO replace by real vehicle categorieco2
                )
                db.insertData(entry)
            } else {
                Toast.makeText(context, "Test", Toast.LENGTH_SHORT).show()
            }
        }

        btn_read.setOnClickListener {
            var data = db.readData()
            tvResult.text = ""
            for (i in 0 until data.size) {
                tvResult.append(data[i].nom.toString() + " " + data[i].prixjournalierbase.toString() + "$ " + data[i].categorieco2.toString())
            }
        }

        btn_delete.setOnClickListener {
            db.deleteData(1)
            btn_read.performClick()
        }
    }
}