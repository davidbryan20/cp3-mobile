package com.example.cp3.telas

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.cp3.R
import com.example.cp3.adapter.MedicamentoAdapter
import com.example.cp3.db.MedicamentoDAO
import com.example.cp3.model.Medicamento

class ListaMedicamentosActivity : AppCompatActivity() {
    private lateinit var listViewMedicamentos: ListView
    private lateinit var medicamentoDAO: MedicamentoDAO
    private lateinit var adapter: MedicamentoAdapter
    private lateinit var listaMedicamentos: MutableList<Medicamento>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_medicamentos)

        listViewMedicamentos = findViewById(R.id.listViewMedicamentos)
        medicamentoDAO = MedicamentoDAO(this)

        // Carregar medicamentos do banco e configurar o adapter
        listaMedicamentos = medicamentoDAO.listarMedicamentos()
        adapter = MedicamentoAdapter(this, listaMedicamentos)
        listViewMedicamentos.adapter = adapter

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
