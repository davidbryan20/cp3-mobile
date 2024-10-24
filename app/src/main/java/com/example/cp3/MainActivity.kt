package com.example.cp3_mobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.cp3.R
import com.example.cp3_mobile.telas.AdicionarMedicamentoActivity
import com.example.cp3_mobile.telas.ListaMedicamentosActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnAdicionarMedicamento: Button
    private lateinit var btnListaMedicamentos: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdicionarMedicamento = findViewById(R.id.btnAdicionarMedicamento)
        btnListaMedicamentos = findViewById(R.id.btnListaMedicamentos)

        // Navegar para Adicionar Medicamento
        btnAdicionarMedicamento.setOnClickListener {
            val intent = Intent(this, AdicionarMedicamentoActivity::class.java)
            startActivity(intent)
        }

        // Navegar para Lista de Medicamentos
        btnListaMedicamentos.setOnClickListener {
            val intent = Intent(this, ListaMedicamentosActivity::class.java)
            startActivity(intent)
        }
    }
}
