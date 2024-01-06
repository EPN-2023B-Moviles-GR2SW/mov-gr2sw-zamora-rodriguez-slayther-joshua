package model.entities

import java.util.Date
import java.util.UUID


class Director(
    var nombre: String,
    var apellido: String,
    private var nacionalidad: String,
    private var fechaNacimiento: Date,
) {
    val id: UUID = UUID.randomUUID()
    val peliculas: MutableList<Pelicula> = mutableListOf()
    override fun toString(): String {
        return "Director(nombre='$nombre', apellido='$apellido', nacionalidad='$nacionalidad', fechaNacimiento=$fechaNacimiento)"
    }
}