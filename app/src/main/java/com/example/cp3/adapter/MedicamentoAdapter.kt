package com.example.cp3_mobile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.BaseAdapter
import com.example.cp3.R
import com.example.cp3_farmacia.model.Medicamento

class MedicamentoAdapter(private val context: Context, private val listaMedicamentos: List<Medicamento>) : BaseAdapter() {

    override fun getCount(): Int {
        return listaMedicamentos.size
    }

    override fun getItem(position: Int): Any {
        return listaMedicamentos[position]
    }

    override fun getItemId(position: Int): Long {
        return listaMedicamentos[position].id
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_medicamento, parent, false)

        val txtNome = view.findViewById<TextView>(R.id.txtNomeMedicamento)
        val txtLaboratorio = view.findViewById<TextView>(R.id.txtLaboratorioMedicamento)
        val txtPreco = view.findViewById<TextView>(R.id.txtPrecoMedicamento)

        val medicamento = listaMedicamentos[position]

        txtNome.text = medicamento.nome
        txtLaboratorio.text = medicamento.laboratorio
        txtPreco.text = "R$ ${medicamento.preco}"

        return view
    }
}
