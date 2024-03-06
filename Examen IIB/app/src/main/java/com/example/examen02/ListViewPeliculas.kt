package com.example.examen02

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.examen02.database.Firestore
import com.example.examen02.database.FirestorePelicula
import com.google.android.material.snackbar.Snackbar

class ListViewPeliculas : AppCompatActivity() {
    var posicionItemSeleccionado = 0
    var imdbIdSeleccionado = ""
    var idDirector = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_peliculas)
        loadPeliculas()

        idDirector = intent.getStringExtra("idDirector")!!
        val nombreDirector = findViewById<TextView>(R.id.txtNombreDirector)
        Firestore.consultarDirector(idDirector!!){
            nombreDirector.text = it.nombre
        }

        val botonVerDirectores = findViewById<Button>(R.id.btnVerDirectores)
        botonVerDirectores.setOnClickListener {
            irActividad(MainActivity::class.java, "0")
        }
        val botonAnadirListView = findViewById<Button>(R.id.btnCrearPelicula)
        botonAnadirListView.setOnClickListener {
            anadirPelicula()
        }
        val listView = findViewById<ListView>(R.id.lvPeliculas)
        registerForContextMenu(listView)

    }

    private fun loadPeliculas() {
        val listView = findViewById<ListView>(
            R.id.lvPeliculas
        )
        idDirector = intent.getStringExtra("idDirector")!!
        FirestorePelicula.consultarPeliculasDirector(idDirector!!) {
            println(it.size)
            if (it != null) {
                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    it!!
                )
                listView.adapter = adapter
                adapter.notifyDataSetChanged()
                registerForContextMenu(listView)
            }
        }
    }

    fun anadirPelicula(
    ){
        idDirector = intent.getStringExtra("idDirector")!!
        irActividad(CrearPelicula::class.java, idDirector!!)
        loadPeliculas()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_pelicula, menu)
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
        idDirector = intent.getStringExtra("idDirector")!!
        FirestorePelicula.consultarPeliculasDirector(idDirector) { imdbIdSeleccionado = it[posicionItemSeleccionado].idIMDB }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.itmEditarPelicula -> {
                irActividad(EditarPelicula::class.java, imdbIdSeleccionado)
                return true
            }
            R.id.itmEliminarPelicula -> {
                abrirDialogo()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(findViewById(R.id.lvPeliculas),
            texto, Snackbar.LENGTH_LONG)
        snack.show()
    }

    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{ dialog, which ->
                eliminarPelicula()
            }
        )

        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val dialogo = builder.create()
        dialogo.show()
    }

    fun eliminarPelicula () {
        FirestorePelicula.eliminarPelicula(imdbIdSeleccionado)
        loadPeliculas()
    }

    fun irActividad (
        clase: Class <*>, id: String
    ) {
        val intent = Intent(this, clase)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}