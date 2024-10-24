package com.example.cp3_mobile.telas

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cp3.R
import com.example.cp3.db.MedicamentoDAO

class DetalhesMedicamentoActivity : AppCompatActivity() {
    private lateinit var txtNomeDetalhes: TextView
    private lateinit var txtLaboratorioDetalhes: TextView
    private lateinit var txtDosagemDetalhes: TextView
    private lateinit var txtTipoDetalhes: TextView
    private lateinit var txtPrecoDetalhes: TextView
    private lateinit var medicamentoDAO: MedicamentoDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_medicamento)

        txtNomeDetalhes = findViewById(R.id.txtNomeDetalhes)
        txtLaboratorioDetalhes = findViewById(R.id.txtLaboratorioDetalhes)
        txtDosagemDetalhes = findViewById(R.id.txtDosagemDetalhes)
        txtTipoDetalhes = findViewById(R.id.txtTipoDetalhes)
        txtPrecoDetalhes = findViewById(R.id.txtPrecoDetalhes)
        medicamentoDAO = MedicamentoDAO(this)

        val medicamentoId = intent.getLongExtra("medicamento_id", -1)
        val medicamento = medicamentoDAO.obterMedicamentoPorId(medicamentoId)

        medicamento?.let {
            txtNomeDetalhes.text = it.nome
            txtLaboratorioDetalhes.text = it.laboratorio
            txtDosagemDetalhes.text = it.dosagem
            txtTipoDetalhes.text = it.tipo
            txtPrecoDetalhes.text = "R$ ${it.preco}"
        }
    }
}
