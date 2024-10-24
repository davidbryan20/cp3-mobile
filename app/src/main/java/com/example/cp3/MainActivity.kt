package com.example.cp3_mobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cp3_mobile.telas.ListaMedicamentosActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Redirecionar para a tela de lista de medicamentos
        val intent = Intent(this, ListaMedicamentosActivity::class.java)
        startActivity(intent)
        finish()
    }
}
