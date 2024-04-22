package com.example.myaccelerometer2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " (" + ID_COL + " INTEGER PRIMARY KEY, " + X_COL + " REAL," + Y_COL + " REAL," + Z_COL + " REAL," + TIMESTAMP_COL + " INTEGER" + ")")
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addOrientation(x: Float, y: Float, z: Float, timestamp: Long) {
        val values = ContentValues()
        values.put(X_COL, x)
        values.put(Y_COL, y)
        values.put(Z_COL, z)
        values.put(TIMESTAMP_COL, timestamp)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }


    fun getOrientation(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    companion object {
        private const val DATABASE_NAME = "MyAccelerometerDB"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "orientation_table"
        const val ID_COL = "id"
        const val X_COL = "x"
        const val Y_COL = "y"
        const val Z_COL = "z"
        const val TIMESTAMP_COL = "timestamp"
    }
}
