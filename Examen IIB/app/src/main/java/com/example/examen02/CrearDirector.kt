package com.example.examen02

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.examen02.database.Firestore
import com.example.examen02.modelo.Director

class CrearDirector : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_director)

        val btnCrearNuevoDirector = findViewById<Button>(R.id.btnCrearNuevoDirector)
        btnCrearNuevoDirector.setOnClickListener{
            crearNuevoDirector()
            irActividad(MainActivity::class.java)
        }

        val btnCancelar = findViewById<Button>(R.id.btnCancelarCrearDirector)
        btnCancelar.setOnClickListener{
            irActividad(MainActivity::class.java)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun crearNuevoDirector(){
        val nombre = findViewById<EditText>(R.id.inputNombre)
        val apellido = findViewById<EditText>(R.id.inputApellido)
        val nacionalidad = findViewById<EditText>(R.id.inputNacionalidad)
        val id = findViewById<EditText>(R.id.inputIdentificador)

        Firestore.crearDirector(
            Director(
                id.text.toString(),
                nombre.text.toString(),
                apellido.text.toString(),
                nacionalidad.text.toString(),
            )
        )

    }

    fun irActividad (
        clase: Class <*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}