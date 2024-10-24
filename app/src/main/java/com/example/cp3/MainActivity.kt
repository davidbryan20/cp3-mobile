package com.example.cp3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.cp3.telas.AdicionarMedicamentoActivity
import com.example.cp3.telas.IntegrantesActivity
import com.example.cp3.telas.ListaMedicamentosActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnAdicionarMedicamento: Button
    private lateinit var btnListaMedicamentos: Button
    private lateinit var btnIntegrantes: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdicionarMedicamento = findViewById(R.id.btnAdicionarMedicamento)
        btnListaMedicamentos = findViewById(R.id.btnListaMedicamentos)
        btnIntegrantes= findViewById(R.id.btnIntegrantes)

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

        btnIntegrantes.setOnClickListener{
            val intent = Intent(this, IntegrantesActivity:: class.java)
            startActivity(intent)
        }
    }
}
