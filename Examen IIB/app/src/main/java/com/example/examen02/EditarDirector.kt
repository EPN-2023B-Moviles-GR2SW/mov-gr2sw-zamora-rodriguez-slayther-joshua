package com.example.examen02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.examen02.database.Firestore
import com.example.examen02.modelo.Director


class EditarDirector : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_director)

        val nombre = findViewById<EditText>(R.id.inputEditarNombre)
        val apellido = findViewById<EditText>(R.id.inputEditarApellido)
        val nacionalidad = findViewById<EditText>(R.id.inputEditarNacionalidad)
        val identificador = findViewById<EditText>(R.id.inputEditarIdentificador)


        var idDirectorSeleccionado = intent.getStringExtra("idDirector")

        Firestore.consultarDirector(idDirectorSeleccionado!!) {
            nombre.setText(it.nombre)
            apellido.setText(it.apellido)
            nacionalidad.setText(it.nacionalidad)
            identificador.setText(it.id)
        }


        val btnEditarDirector = findViewById<Button>(R.id.btnEditarDirector)
        btnEditarDirector.setOnClickListener{
            editarDirector()
            irActividad(MainActivity::class.java)
        }

        val btnCancelar = findViewById<Button>(R.id.btnCancelarEditarDirector)
        btnCancelar.setOnClickListener{
            irActividad(MainActivity::class.java)
        }
    }

    fun editarDirector(){
        val nombre = findViewById<EditText>(R.id.inputEditarNombre)
        val apellido = findViewById<EditText>(R.id.inputEditarApellido)
        val nacionalidad = findViewById<EditText>(R.id.inputEditarNacionalidad)
        val identificador = findViewById<EditText>(R.id.inputEditarIdentificador)

        val directorActualizado = Director(
            identificador.text.toString(),
            nombre.text.toString(),
            apellido.text.toString(),
            nacionalidad.text.toString()
        )
        Firestore.actualizarDirector(directorActualizado)
    }

    fun irActividad (
        clase: Class <*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}