package com.maeldebon.b3android

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "AndroidProject1"
val TABLE_NAME = "Pinned2"
val COL_ID = "id"
val COL_EUID = "euid"
val COL_NOM = "nom"
val COL_IMAGE = "image"
val COL_PRIXJOURNALIERBASE = "prixjournalierbase"
val COL_CATEGORIECO2 = "categorieco2"

class DatabaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_EUID INTEGER, $COL_NOM VARCHAR(256), $COL_IMAGE VARCHAR(256), $COL_PRIXJOURNALIERBASE INTEGER, $COL_CATEGORIECO2 VARCHAR(256))";

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(entry: Entry) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_EUID, entry.euid)
        cv.put(COL_NOM, entry.nom)
        cv.put(COL_IMAGE, entry.image)
        cv.put(COL_PRIXJOURNALIERBASE, entry.prixjournalierbase)
        cv.put(COL_CATEGORIECO2, entry.categorieco2)
        val result = db.insert(TABLE_NAME, null, cv)

        if (result == -1.toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }

    fun readData() : MutableList<Entry> {
        val list : MutableList<Entry> = ArrayList()

        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result = db.rawQuery(query, null)
        if(result.moveToFirst()) {
            do {
                val entry = Entry()
                entry.id = result.getString(result.getColumnIndex((COL_ID))).toInt()
                entry.euid = result.getString(result.getColumnIndex((COL_EUID))).toInt()
                entry.nom = result.getString(result.getColumnIndex((COL_NOM)))
                entry.image = result.getString(result.getColumnIndex((COL_IMAGE)))
                entry.prixjournalierbase = result.getString(result.getColumnIndex((COL_PRIXJOURNALIERBASE))).toInt()
                entry.categorieco2 = result.getString(result.getColumnIndex((COL_CATEGORIECO2)))

                list.add(entry)
            } while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

    fun deleteData(idToDelete : Int) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$COL_ID=?", arrayOf((idToDelete.toString())))
        db.close()
    }
}