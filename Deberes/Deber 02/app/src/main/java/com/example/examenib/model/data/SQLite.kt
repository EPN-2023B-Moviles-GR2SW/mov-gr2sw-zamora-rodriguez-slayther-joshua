package com.example.examenib.model.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLite(context: Context): SQLiteOpenHelper(context, NOMBRE_BASE_DATOS, null, VERSION_BASE_DATOS) {

    companion object {
        const val NOMBRE_BASE_DATOS = "examen.db"
        const val VERSION_BASE_DATOS = 1

        const val SCRIPT_CREATE_TABLE_DIRECTORES = "CREATE TABLE directores (" +
                "director_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre_director TEXT, " +
                "apellido_director TEXT, " +
                "nacionalidad TEXT, " +
                "fecha_nacimiento TEXT)"

        const val SCRIPT_CREATE_TABLE_PELICULAS = "CREATE TABLE peliculas (" +
                "pelicula_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo_pelicula TEXT, " +
                "genero TEXT, " +
                "fecha_estreno TEXT, " +
                "solo_en_cines INTEGER, " +
                "precio REAL, " +
                "director_id INTEGER, " +
                "FOREIGN KEY(director_id) REFERENCES directores(director_id))"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SCRIPT_CREATE_TABLE_DIRECTORES)
        db?.execSQL(SCRIPT_CREATE_TABLE_PELICULAS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE directores")
        db?.execSQL("DROP TABLE peliculas")
        onCreate(db)
    }
}