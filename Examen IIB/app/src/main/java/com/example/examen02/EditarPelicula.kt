package com.example.examen02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.examen02.database.FirestorePelicula
import com.example.examen02.modelo.Pelicula

class EditarPelicula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_pelicula)

        val imdbId = findViewById<EditText>(R.id.inputEditarImdbId)
        val titulo = findViewById<EditText>(R.id.inputEditarTítulo)
        val soloCines = findViewById<EditText>(R.id.inputEditarSoloCines)
        val genero = findViewById<EditText>(R.id.inputEditarGenero)

        val imdbPelicula = intent.getStringExtra("id")
        FirestorePelicula.consultarPelicula(imdbPelicula!!){
            imdbId.setText(it.idIMDB)
            titulo.setText(it.titulo)
            genero.setText(it.genero)
            soloCines.setText(it.soloEnCines.toString())
        }

        val btnEditarPelicula = findViewById<Button>(R.id.btnEditarPelicula)
        btnEditarPelicula.setOnClickListener{
            editarPelicula()
            irActividad(ListViewPeliculas::class.java)
        }

        val btnCancelar = findViewById<Button>(R.id.btnCancelarEditarPelicula)
        btnCancelar.setOnClickListener{
            irActividad(ListViewPeliculas::class.java)
        }
    }

    fun editarPelicula(){
        val imdbId = findViewById<EditText>(R.id.inputEditarImdbId)
        val titulo = findViewById<EditText>(R.id.inputEditarTítulo)
        val soloEnCines = findViewById<EditText>(R.id.inputEditarSoloCines)
        val genero = findViewById<EditText>(R.id.inputEditarGenero)

        val peliculaActualizada = Pelicula (
            imdbId.text.toString(),
            titulo.text.toString(),
            genero.text.toString(),
            soloEnCines.text.toString().toBoolean()
        )
        FirestorePelicula.actualizarPelicula(peliculaActualizada)
    }

    fun irActividad (
        clase: Class <*>
    ) {
        val imdbId = intent.getStringExtra("id")
        FirestorePelicula.consultarPelicula(imdbId!!){
            val intent = Intent(this, clase)
            intent.putExtra("idDirector", it.idDirector)
            startActivity(intent)
        }
    }
}