package com.example.examenib.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.examenib.R
import com.example.examenib.model.data.BaseDatos
import com.example.examenib.model.entities.Director
import java.text.ParseException
import java.time.LocalDate

class FormDirector : AppCompatActivity() {

    lateinit var nombre: String
    lateinit var apellido: String
    lateinit var nacionalidad: String
    lateinit var fechaNacimiento: LocalDate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_director)

        val positionDirectorSelected = intent.getIntExtra("positionDirectorSelected", -1)

        val inputNombreDirector = findViewById<EditText>(R.id.inputNombre)
        val inputApellidoDirector = findViewById<EditText>(R.id.inputApellido)
        val inputNacionalidadDirector = findViewById<EditText>(R.id.inputNacionalidad)
        val inputFechaNacimientoDirector = findViewById<EditText>(R.id.inputDate)
        val btnCrear = findViewById<Button>(R.id.btnAggDirector)
        val btnActualizar = findViewById<Button>(R.id.btnActualizarDirector)

        if (positionDirectorSelected != -1) {
            // Editar
            btnCrear.isEnabled = false
            btnActualizar.isEnabled = true

            inputNombreDirector.setText(BaseDatos.datos[positionDirectorSelected].nombre)
            inputApellidoDirector.setText(BaseDatos.datos[positionDirectorSelected].apellido)
            inputNacionalidadDirector.setText(BaseDatos.datos[positionDirectorSelected].nacionalidad)
            inputFechaNacimientoDirector.setText(BaseDatos.datos[positionDirectorSelected].fechaNacimiento.toString())
            btnActualizar.setOnClickListener {
                try {
                    nombre = inputNombreDirector.text.toString()
                    apellido = inputApellidoDirector.text.toString()
                    nacionalidad = inputNacionalidadDirector.text.toString()
                    fechaNacimiento = LocalDate.parse(inputFechaNacimientoDirector.text.toString())

                    BaseDatos.datos[positionDirectorSelected].nombre = nombre
                    BaseDatos.datos[positionDirectorSelected].apellido = apellido
                    BaseDatos.datos[positionDirectorSelected].nacionalidad = nacionalidad
                    BaseDatos.datos[positionDirectorSelected].fechaNacimiento = fechaNacimiento
                    Toast.makeText(this, "Director actualizado", Toast.LENGTH_SHORT).show()
                    setResult(RESULT_OK, Intent())
                    finish()
                } catch (e: ParseException) {
                    Toast.makeText(this, "Error al parsear la fecha", Toast.LENGTH_SHORT).show()
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
                    nombre = inputNombreDirector.text.toString()
                    apellido = inputApellidoDirector.text.toString()
                    nacionalidad = inputNacionalidadDirector.text.toString()
                    fechaNacimiento = LocalDate.parse(inputFechaNacimientoDirector.text.toString())

                    BaseDatos.datos.add(
                        Director(
                            nombre,
                            apellido,
                            nacionalidad,
                            fechaNacimiento
                        )
                    )
                    Toast.makeText(this, "Director creado", Toast.LENGTH_SHORT).show()
                    setResult(RESULT_OK, Intent())
                    finish()
                } catch (e: ParseException) {
                    Toast.makeText(this, "Error al parsear la fecha", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(this, "Error desconocido", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}