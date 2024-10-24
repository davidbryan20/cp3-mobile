package com.example.cp3_mobile.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.cp3_farmacia.model.Medicamento

class MedicamentoDAO(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "farmacia.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_MEDICAMENTOS = "medicamentos"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NOME = "nome"
        private const val COLUMN_LABORATORIO = "laboratorio"
        private const val COLUMN_DOSAGEM = "dosagem"
        private const val COLUMN_TIPO = "tipo"
        private const val COLUMN_PRECO = "preco"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_MEDICAMENTOS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NOME TEXT,
                $COLUMN_LABORATORIO TEXT,
                $COLUMN_DOSAGEM TEXT,
                $COLUMN_TIPO TEXT,
                $COLUMN_PRECO REAL
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_MEDICAMENTOS")
        onCreate(db)
    }

    // Função para listar todos os medicamentos
    fun listarMedicamentos(): MutableList<Medicamento> {
        val listaMedicamentos = mutableListOf<Medicamento>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_MEDICAMENTOS"
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val nome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME))
                val laboratorio = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LABORATORIO))
                val dosagem = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOSAGEM))
                val tipo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPO))
                val preco = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRECO))

                val medicamento = Medicamento(id, nome, laboratorio, dosagem, tipo, preco)
                listaMedicamentos.add(medicamento)
            } while (cursor.moveToNext())
        }


        cursor.close()
        db.close()

        return listaMedicamentos
    }
    fun obterMedicamentoPorId(id: Long): Medicamento? {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_MEDICAMENTOS WHERE $COLUMN_ID = ?"
        val cursor = db.rawQuery(query, arrayOf(id.toString()))

        var medicamento: Medicamento? = null
        if (cursor.moveToFirst()) {
            val nome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME))
            val laboratorio = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LABORATORIO))
            val dosagem = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOSAGEM))
            val tipo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPO))
            val preco = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRECO))

            medicamento = Medicamento(id, nome, laboratorio, dosagem, tipo, preco)
        }

        cursor.close()
        db.close()

        return medicamento
    }
    fun inserirMedicamento(medicamento: Medicamento) {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_NOME, medicamento.nome)
            put(COLUMN_LABORATORIO, medicamento.laboratorio)
            put(COLUMN_DOSAGEM, medicamento.dosagem)
            put(COLUMN_TIPO, medicamento.tipo)
            put(COLUMN_PRECO, medicamento.preco)
        }
        db.insert(TABLE_MEDICAMENTOS, null, contentValues)
        db.close()
    }
    fun atualizarMedicamento(medicamento: Medicamento) {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_NOME, medicamento.nome)
            put(COLUMN_LABORATORIO, medicamento.laboratorio)
            put(COLUMN_DOSAGEM, medicamento.dosagem)
            put(COLUMN_TIPO, medicamento.tipo)
            put(COLUMN_PRECO, medicamento.preco)
        }
        db.update(TABLE_MEDICAMENTOS, contentValues, "$COLUMN_ID = ?", arrayOf(medicamento.id.toString()))
        db.close()
    }
    }
