package com.example.examenib.model.entities

import java.time.LocalDate

data class Director(
    var nombre: String,
    var apellido: String,
    var nacionalidad: String,
    var fechaNacimiento: LocalDate,
    var peliculas: MutableList<Pelicula> = mutableListOf()
) {
    val id = getId()
    companion object {
        private var idCounter = 0

        fun getId(): Int {
            return idCounter++
        }
    }

    override fun toString(): String {
        return "$id - $nombre $apellido - $nacionalidad"
    }
}