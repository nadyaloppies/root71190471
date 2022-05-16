package id.ac.ukdw.pertemuan10_71190471

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, "myDB", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DatabaseContract.Penduduk.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(DatabaseContract.Penduduk.SQL_DELETE_TABLE)
        onCreate(db)
    }
}