package com.example.cp3_mobile.telas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cp3.R
import com.example.cp3_farmacia.model.Medicamento
import com.example.cp3_mobile.db.MedicamentoDAO


class AdicionarMedicamentoActivity : AppCompatActivity() {
    private lateinit var edtNome: EditText
    private lateinit var edtLaboratorio: EditText
    private lateinit var edtDosagem: EditText
    private lateinit var edtTipo: EditText
    private lateinit var edtPreco: EditText
    private lateinit var btnSalvar: Button
    private lateinit var medicamentoDAO: MedicamentoDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_medicamento)

        edtNome = findViewById(R.id.edtNome)
        edtLaboratorio = findViewById(R.id.edtLaboratorio)
        edtDosagem = findViewById(R.id.edtDosagem)
        edtTipo = findViewById(R.id.edtTipo)
        edtPreco = findViewById(R.id.edtPreco)
        btnSalvar = findViewById(R.id.btnSalvar)
        medicamentoDAO = MedicamentoDAO(this)

        val medicamentoId = intent.getLongExtra("medicamento_id", -1)

        // Se estiver editando, preencher os campos
        if (medicamentoId != -1L) {
            val medicamento = medicamentoDAO.obterMedicamentoPorId(medicamentoId)
            medicamento?.let {
                edtNome.setText(it.nome)
                edtLaboratorio.setText(it.laboratorio)
                edtDosagem.setText(it.dosagem)
                edtTipo.setText(it.tipo)
                edtPreco.setText(it.preco.toString())
            }
        }

        btnSalvar.setOnClickListener {
            val nome = edtNome.text.toString()
            val laboratorio = edtLaboratorio.text.toString()
            val dosagem = edtDosagem.text.toString()
            val tipo = edtTipo.text.toString()
            val preco = edtPreco.text.toString().toDoubleOrNull() ?: 0.0

            if (nome.isNotBlank() && laboratorio.isNotBlank()) {
                val medicamento = Medicamento(medicamentoId, nome, laboratorio, dosagem, tipo, preco)

                if (medicamentoId == -1L) {
                    medicamentoDAO.inserirMedicamento(medicamento)
                    Toast.makeText(this, "Medicamento adicionado", Toast.LENGTH_SHORT).show()
                } else {
                    medicamentoDAO.atualizarMedicamento(medicamento)
                    Toast.makeText(this, "Medicamento atualizado", Toast.LENGTH_SHORT).show()
                }
                finish()
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
