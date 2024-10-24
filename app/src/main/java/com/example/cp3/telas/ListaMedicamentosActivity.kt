package com.example.cp3_mobile.telas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.cp3.R
import com.example.cp3_mobile.adapter.MedicamentoAdapter
import com.example.cp3_mobile.db.MedicamentoDAO

class ListaMedicamentosActivity : AppCompatActivity() {
    private lateinit var listViewMedicamentos: ListView
    private lateinit var btnAdicionarMedicamento: Button
    private lateinit var medicamentoDAO: MedicamentoDAO
    private lateinit var adapter: MedicamentoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_medicamentos)

        listViewMedicamentos = findViewById(R.id.listViewMedicamentos)
        btnAdicionarMedicamento = findViewById(R.id.btnAdicionarMedicamento)
        medicamentoDAO = MedicamentoDAO(this)

        // Carregar medicamentos do banco e configurar o adapter
        val listaMedicamentos = medicamentoDAO.listarMedicamentos()
        adapter = MedicamentoAdapter(this, listaMedicamentos)
        listViewMedicamentos.adapter = adapter

        // Ação para adicionar novo medicamento
        btnAdicionarMedicamento.setOnClickListener {
            val intent = Intent(this, AdicionarMedicamentoActivity::class.java)
            startActivity(intent)
        }

        // Ação para visualizar detalhes de um medicamento
        listViewMedicamentos.setOnItemClickListener { _, _, position, _ ->
            val medicamento = listaMedicamentos[position]
            val intent = Intent(this, DetalhesMedicamentoActivity::class.java)
            intent.putExtra("medicamento_id", medicamento.id)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Atualizar a lista quando voltar para a tela
        adapter.clear()
        adapter.addAll(medicamentoDAO.listarMedicamentos())
        adapter.notifyDataSetChanged()
    }
}
