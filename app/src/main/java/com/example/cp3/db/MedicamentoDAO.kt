package com.example.cp3.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.cp3_farmacia.model.Medicamento

class MedicamentoDAO(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "farmacia_db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_MEDICAMENTOS = "medicamentos"

        private const val COLUMN_ID = "id"
        private const val COLUMN_NOME = "nome"
        private const val COLUMN_LABORATORIO = "laboratorio"
        private const val COLUMN_DOSAGEM = "dosagem"
        private const val COLUMN_TIPO = "tipo"
        private const val COLUMN_PRECO = "preco"
    }

    override fun onCreate(db: SQLiteDatabase?) {
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
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_MEDICAMENTOS")
        onCreate(db)
    }

    fun inserirMedicamento(medicamento: Medicamento): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOME, medicamento.nome)
            put(COLUMN_LABORATORIO, medicamento.laboratorio)
            put(COLUMN_DOSAGEM, medicamento.dosagem)
            put(COLUMN_TIPO, medicamento.tipo)
            put(COLUMN_PRECO, medicamento.preco)
        }
        return db.insert(TABLE_MEDICAMENTOS, null, values).also { db.close() }
    }

    fun obterMedicamentoPorId(id: Long): Medicamento? {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_MEDICAMENTOS,
            null,
            "$COLUMN_ID = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )
        return if (cursor.moveToFirst()) {
            val medicamento = Medicamento(
                id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                nome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME)),
                laboratorio = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LABORATORIO)),
                dosagem = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOSAGEM)),
                tipo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPO)),
                preco = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRECO))
            )
            cursor.close()
            db.close()
            medicamento
        } else {
            cursor.close()
            db.close()
            null
        }
    }

    fun listarMedicamentos(): List<Medicamento> {
        val listaMedicamentos = mutableListOf<Medicamento>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_MEDICAMENTOS", null)
        if (cursor.moveToFirst()) {
            do {
                val medicamento = Medicamento(
                    id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    nome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME)),
                    laboratorio = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LABORATORIO)),
                    dosagem = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOSAGEM)),
                    tipo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPO)),
                    preco = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRECO))
                )
                listaMedicamentos.add(medicamento)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return listaMedicamentos
    }
}
