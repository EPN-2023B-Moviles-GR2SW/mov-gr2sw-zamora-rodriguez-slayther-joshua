package com.example.examenib.model.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.example.examenib.model.data.SQLite
import com.example.examenib.model.entities.Pelicula
import java.time.LocalDate

class PeliculaDAO(context: Context) {

    private val db = SQLite(context).writableDatabase

    fun insertarPelicula(pelicula: Pelicula, idDirector: Int) {
        val values = ContentValues().apply {
            put("titulo_pelicula", pelicula.titulo)
            put("genero", pelicula.genero)
            put("fecha_estreno", pelicula.fechaEstreno.toString())
            put("solo_en_cines", pelicula.soloEnCines)
            put("precio", pelicula.precio)
            put("director_id", idDirector)
        }
        db.insert("peliculas", null, values)
    }

    @SuppressLint("Range")
    fun obtenerPeliculas(): MutableList<Pelicula> {
        val listaPeliculas = mutableListOf<Pelicula>()
        val cursor = db.query(
            "peliculas",
            null,
            null,
            null,
            null,
            null,
            null
        )

        while (cursor.moveToNext()) {
            val idPelicula = cursor.getInt(cursor.getColumnIndex("pelicula_id"))
            val tituloPelicula =
                cursor.getString(cursor.getColumnIndex("titulo_pelicula"))
            val genero =
                cursor.getString(cursor.getColumnIndex("genero"))
            val fechaEstreno = cursor.getString(cursor.getColumnIndex("fecha_estreno"))
            val soloEnCines = cursor.getInt(cursor.getColumnIndex("solo_en_cines"))
            val precio = cursor.getDouble(cursor.getColumnIndex("precio"))

            val pelicula = Pelicula(idPelicula, tituloPelicula, genero, LocalDate.parse(fechaEstreno), soloEnCines != 0, precio)
            listaPeliculas.add(pelicula)
        }
        cursor.close()
        return listaPeliculas
    }

    @SuppressLint("Range")
    fun obtenerPeliculasPorDirector(idDirector: Int): MutableList<Pelicula> {
        val listaPeliculas = mutableListOf<Pelicula>()
        val cursor = db.query(
            "peliculas",
            null,
            "director_id = ?",
            arrayOf(idDirector.toString()),
            null,
            null,
            null
        )

        while (cursor.moveToNext()) {
            val idPelicula = cursor.getInt(cursor.getColumnIndex("pelicula_id"))
            val tituloPelicula =
                cursor.getString(cursor.getColumnIndex("titulo_pelicula"))
            val genero =
                cursor.getString(cursor.getColumnIndex("genero"))
            val fechaEstreno = cursor.getString(cursor.getColumnIndex("fecha_estreno"))
            val soloEnCines = cursor.getInt(cursor.getColumnIndex("solo_en_cines"))
            val precio = cursor.getDouble(cursor.getColumnIndex("precio"))

            val pelicula = Pelicula(idPelicula, tituloPelicula, genero, LocalDate.parse(fechaEstreno), soloEnCines != 0, precio)
            listaPeliculas.add(pelicula)
        }
        cursor.close()
        return listaPeliculas
    }


    fun actualizarPelicula(pelicula: Pelicula) {
        val values = ContentValues().apply {
            put("titulo_pelicula", pelicula.titulo)
            put("genero", pelicula.genero)
            put("fecha_estreno", pelicula.fechaEstreno.toString())
            put("solo_en_cines", pelicula.soloEnCines)
            put("precio", pelicula.precio)
        }
        db.update(
            "peliculas",
            values,
            "pelicula_id = ?",
            arrayOf(pelicula.id.toString())
        )
    }

    fun eliminarPelicula(idPelicula: Int) {
        db.delete(
            "peliculas",
            "pelicula_id = ?",
            arrayOf(idPelicula.toString())
        )
    }
}