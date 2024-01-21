package com.example.examenib.model.entities

import java.time.LocalDate

data class Pelicula(
    var titulo: String,
    var genero: String,
    var fechaEstreno: LocalDate,
    var soloEnCines: Boolean,
    var precio: Double
) {
    val id = getId()
    companion object {
        private var idCounter = 0

        fun getId(): Int {
            return idCounter++
        }
    }

    override fun toString(): String {
        return "$id - $titulo - $genero"
    }
}