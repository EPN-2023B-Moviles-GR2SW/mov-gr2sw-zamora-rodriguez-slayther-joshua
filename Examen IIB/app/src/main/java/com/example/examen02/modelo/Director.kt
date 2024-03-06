package com.example.examen02.modelo

import java.time.LocalDate

class Director(
    val id: String,
    val nombre: String,
    val apellido: String,
    val nacionalidad: String,
    var peliculas: MutableList<Pelicula>? = null
) {
    override fun toString(): String {
        return "Nombre : $nombre\nApellido : $apellido\nNacionalidad : $nacionalidad"
    }
}