package com.example.examenib.model.data

import com.example.examenib.model.entities.Director
import com.example.examenib.model.entities.Pelicula
import java.time.LocalDate

object BaseDatos {

    private val inception = Pelicula(
        "Inception",
        "Ciencia ficción",
        LocalDate.of(2010, 7, 8),
        true,
        12.99
    )

    private val interstellar = Pelicula(
        "Interstellar",
        "Ciencia ficción",
        LocalDate.of(2014, 10, 26),
        true,
        14.99
    )

    private val darkKnight = Pelicula(
        "The Dark Knight",
        "Acción", LocalDate.of(2008, 7, 18),
        false,
        9.99
    )

    private val pulpFiction = Pelicula(
        "Pulp Fiction",
        "Crimen",
        LocalDate.of(1994, 5, 12),
        false,
        11.99
    )

    private val killBill = Pelicula(
        "Kill Bill: Volume 1",
        "Acción",
        LocalDate.of(2003, 10, 10),
        true,
        13.99
    )

    private val djangoUnchained = Pelicula(
        "Django Unchained",
        "Drama",
        LocalDate.of(2012, 12, 25),
        true,
        15.99
    )

    private val nolan = Director(
        "Christopher",
        "Nolan",
        "Británico",
        LocalDate.of(1970, 7, 30),
        mutableListOf(inception, interstellar, darkKnight)
    )

    private val tarantino = Director(
        "Quentin",
        "Tarantino",
        "Estadounidense",
        LocalDate.of(1963, 3, 27),
        mutableListOf(pulpFiction, killBill, djangoUnchained)
    )

    val datos = listOf(nolan, tarantino).toMutableList()

}