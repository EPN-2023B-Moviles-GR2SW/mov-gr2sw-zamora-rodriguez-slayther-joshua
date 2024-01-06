package model.entities

import java.util.Date
import java.util.UUID

class Pelicula(
    var titulo: String,
    private var genero: String,
    private var fechaEstreno: Date,
    private var soloEnCines: Boolean,
    private var precio: Double
) {
    val id: UUID = UUID.randomUUID()
    override fun toString(): String {
        return "Pelicula(titulo='$titulo', genero='$genero', fechaEstreno=$fechaEstreno, soloEnCines=$soloEnCines, precio=$precio)"
    }
}