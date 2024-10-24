package com.example.cp3.telas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.cp3.R
import com.example.cp3.db.MedicamentoDAO

class DetalhesMedicamentoActivity : AppCompatActivity() {
    private lateinit var txtNomeDetalhes: TextView
    private lateinit var txtLaboratorioDetalhes: TextView
    private lateinit var txtDosagemDetalhes: TextView
    private lateinit var txtTipoDetalhes: TextView
    private lateinit var txtPrecoDetalhes: TextView
    private lateinit var btnAtualizarMedicamento: Button
    private lateinit var btnExcluirMedicamento: Button
    private lateinit var medicamentoDAO: MedicamentoDAO
    private var medicamentoId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_medicamento)

        txtNomeDetalhes = findViewById(R.id.txtNomeDetalhes)
        txtLaboratorioDetalhes = findViewById(R.id.txtLaboratorioDetalhes)
        txtDosagemDetalhes = findViewById(R.id.txtDosagemDetalhes)
        txtTipoDetalhes = findViewById(R.id.txtTipoDetalhes)
        txtPrecoDetalhes = findViewById(R.id.txtPrecoDetalhes)
        btnAtualizarMedicamento = findViewById(R.id.btnAtualizarMedicamento)
        btnExcluirMedicamento = findViewById(R.id.btnExcluirMedicamento)
        medicamentoDAO = MedicamentoDAO(this)

        medicamentoId = intent.getLongExtra("medicamento_id", -1)
        val medicamento = medicamentoDAO.obterMedicamentoPorId(medicamentoId)

        medicamento?.let {
            txtNomeDetalhes.text = it.nome
            txtLaboratorioDetalhes.text = it.laboratorio
            txtDosagemDetalhes.text = it.dosagem
            txtTipoDetalhes.text = it.tipo
            txtPrecoDetalhes.text = "R$ ${it.preco}"
        }

        // Ação para atualizar o medicamento
        btnAtualizarMedicamento.setOnClickListener {
            val intent = Intent(this, AdicionarMedicamentoActivity::class.java)
            intent.putExtra("medicamento_id", medicamentoId) // Passa o ID do medicamento para a tela de atualização
            startActivity(intent)
        }

        // Ação para excluir o medicamento
        btnExcluirMedicamento.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Confirmar Exclusão")
                .setMessage("Você tem certeza que deseja excluir ${medicamento?.nome}?")
                .setPositiveButton("Sim") { dialog, _ ->
                    medicamentoDAO.excluirMedicamento(medicamentoId)
                    Toast.makeText(this, "Medicamento excluído com sucesso", Toast.LENGTH_SHORT).show()
                    finish() // Fecha a activity e volta para a lista
                }
                .setNegativeButton("Não") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }
}
