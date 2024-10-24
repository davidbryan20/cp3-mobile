package com.example.cp3_mobile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.BaseAdapter
import com.example.cp3.R
import com.example.cp3_farmacia.model.Medicamento


class MedicamentoAdapter(
    private val context: Context,
    private var listaMedicamentos: MutableList<Medicamento>
) : BaseAdapter() {

    override fun getCount(): Int {
        return listaMedicamentos.size
    }

    override fun getItem(position: Int): Medicamento {
        return listaMedicamentos[position]
    }

    override fun getItemId(position: Int): Long {
        return listaMedicamentos[position].id
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_medicamento, parent, false)

        val medicamento = getItem(position)

        val txtNome = view.findViewById<TextView>(R.id.txtNome)
        val txtLaboratorio = view.findViewById<TextView>(R.id.txtLaboratorio)
        val txtDosagem = view.findViewById<TextView>(R.id.txtDosagem)
        val txtTipo = view.findViewById<TextView>(R.id.txtTipo)
        val txtPreco = view.findViewById<TextView>(R.id.txtPreco)

        txtNome.text = medicamento.nome
        txtLaboratorio.text = medicamento.laboratorio
        txtDosagem.text = medicamento.dosagem
        txtTipo.text = medicamento.tipo
        txtPreco.text = "R$ ${medicamento.preco}"

        return view
    }

    fun clear() {
        listaMedicamentos.clear()
        notifyDataSetChanged()
    }

    fun addAll(medicamentos: List<Medicamento>) {
        listaMedicamentos.addAll(medicamentos)
        notifyDataSetChanged()
    }
}
