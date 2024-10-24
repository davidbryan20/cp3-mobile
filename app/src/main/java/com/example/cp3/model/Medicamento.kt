package com.example.cp3.model

data class Medicamento(
    var id: Long = 0,
    var nome: String,
    var laboratorio: String,
    var dosagem: String,
    var tipo: String,
    var preco: Double
)
