package br.com.paulosalvatore.ocean_a7_06_11_18.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import br.com.paulosalvatore.ocean_a7_06_11_18.MainActivity

fun inicializarInstancia() {
    DatabaseManager(MainActivity())
}

class DatabaseManager(context: Context) {
    companion object {
        fun getInstancia(context: Context) = DatabaseManager(context)
    }

    private val helper = DatabaseHelper(context)
    private lateinit var db: SQLiteDatabase

    fun abrirConexao() {
        db = helper.writableDatabase
    }

    fun fecharConexao() {
        if (db.isOpen) {
            db.close()
        }
    }

    fun criarPosicao(posicao: Posicao) {
        val contentValues = ContentValues()
        contentValues.put("latitude", posicao.latitude)
        contentValues.put("longitude", posicao.longitude)
        contentValues.put("data_hora", posicao.dataHora)

        abrirConexao()
        val id = db.insert("posicoes", null, contentValues)
        fecharConexao()

        posicao.id = id.toInt()
    }

    fun obterPosicoes(): List<Posicao> {
        val posicoes = mutableListOf<Posicao>()

        val sql = "SELECT * FROM posicoes"
        abrirConexao()
        val cursor = db.rawQuery(sql, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val latitude = cursor.getDouble(cursor.getColumnIndex("latitude"))
                val longitude = cursor.getDouble(cursor.getColumnIndex("longitude"))
                val dataHora = cursor.getString(cursor.getColumnIndex("data_hora"))

                val posicao = Posicao(id, latitude, longitude, dataHora)
                posicoes.add(posicao)
            } while (cursor.moveToNext())

            cursor.close()
        }

        return posicoes
    }

    fun editarPosicao(posicao: Posicao) {
        val contentValues = ContentValues()
        contentValues.put("latitude", posicao.latitude)
        contentValues.put("longitude", posicao.longitude)
        contentValues.put("data_hora", posicao.dataHora)

        abrirConexao()
        db.update("posicoes", contentValues, "id=?", arrayOf(posicao.id.toString()))
        fecharConexao()
    }

    fun removerPosicao(posicao: Posicao) {
        if (posicao.id == 0) return

        val sql = "DELETE FROM posicoes WHERE (id = ${posicao.id});"

        abrirConexao()
        db.execSQL(sql)
        fecharConexao()
    }

    fun limparPosicoes() {
        val sql = "DELETE FROM posicoes"

        abrirConexao()
        db.execSQL(sql)
        fecharConexao()
    }
}
