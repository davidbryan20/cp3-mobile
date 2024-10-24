package com.example.cp3.telas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cp3.R
import com.example.cp3.db.MedicamentoDAO
import com.example.cp3.model.Medicamento

class AdicionarMedicamentoActivity : AppCompatActivity() {
    private lateinit var editNome: EditText
    private lateinit var editLaboratorio: EditText
    private lateinit var editDosagem: EditText
    private lateinit var editTipo: EditText
    private lateinit var editPreco: EditText
    private lateinit var btnSalvar: Button
    private lateinit var medicamentoDAO: MedicamentoDAO
    private var medicamentoId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_medicamento)

        editNome = findViewById(R.id.editNome)
        editLaboratorio = findViewById(R.id.editLaboratorio)
        editDosagem = findViewById(R.id.editDosagem)
        editTipo = findViewById(R.id.editTipo)
        editPreco = findViewById(R.id.editPreco)
        btnSalvar = findViewById(R.id.btnSalvar)
        medicamentoDAO = MedicamentoDAO(this)

        medicamentoId = intent.getLongExtra("medicamento_id", -1)
        if (medicamentoId != -1L) {
            // Carrega os dados do medicamento para edição
            val medicamento = medicamentoDAO.obterMedicamentoPorId(medicamentoId)
            medicamento?.let {
                editNome.setText(it.nome)
                editLaboratorio.setText(it.laboratorio)
                editDosagem.setText(it.dosagem)
                editTipo.setText(it.tipo)
                editPreco.setText(it.preco.toString())
            }
        }

        btnSalvar.setOnClickListener {
            // Salvar lógica, seja para adicionar ou atualizar
            val nome = editNome.text.toString()
            val laboratorio = editLaboratorio.text.toString()
            val dosagem = editDosagem.text.toString()
            val tipo = editTipo.text.toString()
            val preco = editPreco.text.toString().toDoubleOrNull()

            if (preco != null) {
                if (medicamentoId != -1L) {
                    // Atualiza medicamento
                    val medicamentoAtualizado = Medicamento(medicamentoId, nome, laboratorio, dosagem, tipo, preco)
                    medicamentoDAO.atualizarMedicamento(medicamentoAtualizado)
                    Toast.makeText(this, "Medicamento atualizado com sucesso", Toast.LENGTH_SHORT).show()
                } else {
                    // Adiciona novo medicamento
                    val novoMedicamento = Medicamento(nome = nome, laboratorio = laboratorio, dosagem = dosagem, tipo = tipo, preco = preco)
                    medicamentoDAO.inserirMedicamento(novoMedicamento)
                    Toast.makeText(this, "Medicamento adicionado com sucesso", Toast.LENGTH_SHORT).show()
                }
                finish() // Volta para a lista de medicamentos
            } else {
                Toast.makeText(this, "Preço inválido", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
