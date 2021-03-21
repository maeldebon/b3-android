package com.maeldebon.b3android

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "AndroidProject"
val TABLE_NAME = "Pinned"
val COL_EUID = "euid"
val COL_ID = "id"

class DatabaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_EUID INTEGER)";

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(entry: Entry) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_EUID, entry.euid)
        var result = db.insert(TABLE_NAME, null, cv)

        if (result == -1.toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }

    fun readData() : MutableList<Entry> {
        var list : MutableList<Entry> = ArrayList()

        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result = db.rawQuery(query, null)
        if(result.moveToFirst()) {
            do {
                var entry = Entry()
                entry.id = result.getString(result.getColumnIndex((COL_ID))).toInt()
                entry.euid = result.getString(result.getColumnIndex((COL_EUID))).toInt()
                // entry.name = result.getString(1)

                list.add(entry)
            } while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

    fun deleteData(idToDelete : Int) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, COL_ID+"=?", arrayOf((idToDelete.toString())))
        db.close()
    }
}