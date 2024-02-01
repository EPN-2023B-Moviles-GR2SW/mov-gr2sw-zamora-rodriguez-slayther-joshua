package com.example.examenib.model.entities

import java.time.LocalDate

data class Director(
    var id: Int,
    var nombre: String,
    var apellido: String,
    var nacionalidad: String,
    var fechaNacimiento: LocalDate,
    var peliculas: MutableList<Pelicula> = mutableListOf()
) {
    override fun toString(): String {
        return "$id - $nombre $apellido - $nacionalidad"
    }
}