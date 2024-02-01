package com.example.examenib.model.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.example.examenib.model.data.SQLite
import com.example.examenib.model.entities.Director
import java.time.LocalDate

class DirectorDAO(context: Context) {

    private val db = SQLite(context).writableDatabase
    private val peliculaDAO = PeliculaDAO(context)

    fun insertarDirector(director: Director) {
        val values = ContentValues().apply {
            put("nombre_director", director.nombre)
            put("apellido_director", director.apellido)
            put("nacionalidad", director.nacionalidad)
            put("fecha_nacimiento", director.fechaNacimiento.toString())
        }
        db.insert("directores", null, values)
    }

    @SuppressLint("Range")
    fun obtenerDirectores(): List<Director> {
        val listaDirectores = mutableListOf<Director>()
        val cursor = db.query(
            "directores",
            null,
            null,
            null,
            null,
            null,
            null
        )

        while (cursor.moveToNext()) {
            val idDirector = cursor.getInt(cursor.getColumnIndex("director_id"))
            val nombreDirector =
                cursor.getString(cursor.getColumnIndex("nombre_director"))
            val apellidoDirector =
                cursor.getString(cursor.getColumnIndex("apellido_director"))
            val nacionalidad = cursor.getString(cursor.getColumnIndex("nacionalidad"))
            val fechaNacimiento = cursor.getString(cursor.getColumnIndex("fecha_nacimiento"))
            val peliculas = peliculaDAO.obtenerPeliculasPorDirector(idDirector)
            val director =
                Director(idDirector, nombreDirector, apellidoDirector, nacionalidad, LocalDate.parse(fechaNacimiento), peliculas)
            listaDirectores.add(director)
        }
        cursor.close()
        return listaDirectores
    }

    fun actualizarDirector(director: Director) {
        val values = ContentValues().apply {
            put("nombre_director", director.nombre)
            put("apellido_director", director.apellido)
            put("nacionalidad", director.nacionalidad)
            put("fecha_nacimiento", director.fechaNacimiento.toString())
        }
        db.update(
            "directores",
            values,
            "director_id = ?",
            arrayOf(director.id.toString())
        )
    }

    fun eliminarDirector(idDirector: Int) {
        db.delete(
            "directores",
            "director_id = ?",
            arrayOf(idDirector.toString())
        )
    }
}