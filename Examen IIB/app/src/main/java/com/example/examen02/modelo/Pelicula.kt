package com.example.examen02.modelo

class Pelicula (
    val idIMDB: String,
    val titulo: String,
    val genero: String,
    var soloEnCines: Boolean,
    var idDirector: String? = null,
) {
    override fun toString(): String {
        return "Titulo : $titulo\nGenero : $genero\nSolo en cines : $soloEnCines"
    }
}