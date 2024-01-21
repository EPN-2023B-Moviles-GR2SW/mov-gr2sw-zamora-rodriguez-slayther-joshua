package com.example.examenib.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.examenib.R
import com.example.examenib.model.data.BaseDatos
import com.example.examenib.model.entities.Pelicula
import java.text.ParseException
import java.time.LocalDate

class FormPelicula : AppCompatActivity() {

    lateinit var titulo: String
    lateinit var genero: String
    var soloEnCines = false
    lateinit var fechaEstreno: LocalDate
    var precio = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_pelicula)

        val positionPeliculaSelected = intent.getIntExtra("positionPeliculaSelected", -1)
        val positionDirectorSelected = intent.getIntExtra("positionDirectorSelected", -1)

        val inputTituloPelicula = findViewById<EditText>(R.id.inputTitulo)
        val inputGeneroPelicula = findViewById<EditText>(R.id.inputGenero)
        val inputSoloEnCinesPelicula = findViewById<EditText>(R.id.inputSoloCines)
        val inputFechaEstrenoPelicula = findViewById<EditText>(R.id.inputDate)
        val inputPrecioPelicula = findViewById<EditText>(R.id.inputPrecio)
        val btnCrear = findViewById<Button>(R.id.btnAggPelicula)
        val btnActualizar = findViewById<Button>(R.id.btnActualizarPelicula)

        if (positionPeliculaSelected != -1) {
            // Editar
            btnActualizar.isEnabled = true
            btnCrear.isEnabled = false

            inputTituloPelicula.setText(BaseDatos.datos[positionDirectorSelected].peliculas[positionPeliculaSelected].titulo)
            inputGeneroPelicula.setText(BaseDatos.datos[positionDirectorSelected].peliculas[positionPeliculaSelected].genero)
            inputSoloEnCinesPelicula.setText(BaseDatos.datos[positionDirectorSelected].peliculas[positionPeliculaSelected].soloEnCines.toString())
            inputFechaEstrenoPelicula.setText(BaseDatos.datos[positionDirectorSelected].peliculas[positionPeliculaSelected].fechaEstreno.toString())
            inputPrecioPelicula.setText(BaseDatos.datos[positionDirectorSelected].peliculas[positionPeliculaSelected].precio.toString())

            btnActualizar.setOnClickListener {
                try {
                    titulo = inputTituloPelicula.text.toString()
                    genero = inputGeneroPelicula.text.toString()
                    soloEnCines = inputSoloEnCinesPelicula.text.toString().toBoolean()
                    fechaEstreno = LocalDate.parse(inputFechaEstrenoPelicula.text.toString())
                    precio = inputPrecioPelicula.text.toString().toDouble()

                    BaseDatos.datos[positionDirectorSelected].peliculas[positionPeliculaSelected].titulo =
                        titulo
                    BaseDatos.datos[positionDirectorSelected].peliculas[positionPeliculaSelected].genero =
                        genero
                    BaseDatos.datos[positionDirectorSelected].peliculas[positionPeliculaSelected].soloEnCines =
                        soloEnCines
                    BaseDatos.datos[positionDirectorSelected].peliculas[positionPeliculaSelected].fechaEstreno =
                        fechaEstreno
                    BaseDatos.datos[positionDirectorSelected].peliculas[positionPeliculaSelected].precio =
                        precio
                    Toast.makeText(this, "Pelicula actualizada", Toast.LENGTH_SHORT).show()
                    setResult(RESULT_OK, Intent())
                    finish()
                } catch (e: ParseException) {
                    Toast.makeText(this, "Error al parsear la fecha", Toast.LENGTH_SHORT).show()

                } catch (e: NumberFormatException) {
                    Toast.makeText(
                        this,
                        "Error al convertir el precio a número",
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    Toast.makeText(this, "Error desconocido", Toast.LENGTH_SHORT).show()
                }

            }
        } else {
            // Crear
            btnCrear.isEnabled = true
            btnActualizar.isEnabled = false

            btnCrear.setOnClickListener {
                try {
                    titulo = inputTituloPelicula.text.toString()
                    genero = inputGeneroPelicula.text.toString()
                    soloEnCines = inputSoloEnCinesPelicula.text.toString().toBoolean()
                    fechaEstreno = LocalDate.parse(inputFechaEstrenoPelicula.text.toString())
                    precio = inputPrecioPelicula.text.toString().toDouble()

                    BaseDatos.datos[positionDirectorSelected].peliculas.add(
                        Pelicula(
                            titulo,
                            genero,
                            fechaEstreno,
                            soloEnCines,
                            precio
                        )
                    )
                    Toast.makeText(this, "Pelicula creada", Toast.LENGTH_SHORT).show()
                    setResult(RESULT_OK, Intent())
                    finish()
                } catch (e: ParseException) {
                    Toast.makeText(this, "Error al parsear la fecha", Toast.LENGTH_SHORT).show()

                } catch (e: NumberFormatException) {
                    Toast.makeText(
                        this,
                        "Error al convertir el precio a número",
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    Toast.makeText(this, "Error desconocido", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}