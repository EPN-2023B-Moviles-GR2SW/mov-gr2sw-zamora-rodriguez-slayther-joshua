package com.example.examenib.model.entities

import java.time.LocalDate

data class Pelicula(
    var id: Int,
    var titulo: String,
    var genero: String,
    var fechaEstreno: LocalDate,
    var soloEnCines: Boolean,
    var precio: Double
) {
    override fun toString(): String {
        return "$id - $titulo - $genero"
    }
}