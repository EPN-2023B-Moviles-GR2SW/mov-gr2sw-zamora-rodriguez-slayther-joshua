package com.example.examen02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.examen02.database.FirestorePelicula
import com.example.examen02.modelo.Pelicula

class CrearPelicula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_pelicula)

        var idDirector = intent.getStringExtra("id")
        val btnCrearNuevaPelicula = findViewById<Button>(R.id.btnCrearNuevaPelicula)
        btnCrearNuevaPelicula.setOnClickListener{
            crarNuevaPelicula()
            irActividad(ListViewPeliculas::class.java, idDirector!!)
        }

        val btnCancelar = findViewById<Button>(R.id.btnCancelarCrearPelicula)
        btnCancelar.setOnClickListener{
            irActividad(ListViewPeliculas::class.java, idDirector!!)
        }
    }

    fun crarNuevaPelicula(){
        val idImdb = findViewById<EditText>(R.id.inputImdbId)
        val titulo = findViewById<EditText>(R.id.inputTitulo)
        val genero = findViewById<EditText>(R.id.inputGenero)
        val soloCines = findViewById<EditText>(R.id.inputSoloCines)

        val nuevaPelicula = Pelicula(
            idImdb.text.toString(),
            titulo.text.toString(),
            genero.text.toString(),
            soloCines.text.toString().toBoolean(),
        )

        var idDirector = intent.getStringExtra("id")
        FirestorePelicula.crearPelicula(nuevaPelicula, idDirector!!)
    }

    fun irActividad (
        clase: Class <*>, idDirector: String
    ) {
        val intent = Intent(this, clase)
        intent.putExtra("idDirector", idDirector)
        startActivity(intent)
    }
}