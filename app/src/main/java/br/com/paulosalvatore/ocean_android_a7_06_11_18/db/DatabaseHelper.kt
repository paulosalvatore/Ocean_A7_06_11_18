package br.com.paulosalvatore.ocean_a7_06_11_18.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(val context: Context) :
    SQLiteOpenHelper(context, "ocean_db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            db?.execSQL("CREATE TABLE posicoes (" +
                    "id integer PRIMARY KEY," +
                    "latitude double," +
                    "longitude double," +
                    "data_hora varchar(255));")
        } catch (e: Exception) {
            Log.e("BANCO_DADOS", "Erro ao criar banco de dados", e)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}
