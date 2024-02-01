package com.example.examenib.controller

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.examenib.R
import com.example.examenib.model.dao.DirectorDAO
import com.example.examenib.model.dao.PeliculaDAO
import com.example.examenib.model.entities.Pelicula

class ListaPeliculas : AppCompatActivity() {

    val peliculaDAO = PeliculaDAO(this)
    val directorDAO = DirectorDAO(this)
    var positionPeliculaSelected = -1
    var positionDirectorSelected = -1
    lateinit var adapter: ArrayAdapter<Pelicula>

    val callbackContenido =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                if (result.data != null) {
                    adapter.notifyDataSetChanged()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_peliculas)

        positionDirectorSelected = intent.getIntExtra("positionDirectorSelected", -1)

        val txtDirector = findViewById<TextView>(R.id.txtPeliculasDirector)
        txtDirector.text =
            "Películas de ${directorDAO.obtenerDirectores()[positionDirectorSelected].nombre} ${directorDAO.obtenerDirectores()[positionDirectorSelected].apellido}"
        val listView = findViewById<ListView>(R.id.lvPeliculas)


        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            directorDAO.obtenerDirectores()[positionDirectorSelected].peliculas
        )
        listView.adapter = adapter
        adapter.notifyDataSetChanged()

        val btnCrearPelicula = findViewById<TextView>(R.id.btnCrearPelicula)
        btnCrearPelicula.setOnClickListener {
            val intent = Intent(this, FormPelicula::class.java)
            intent.putExtra("positionPeliculaSelected", -1)
            intent.putExtra("positionDirectorSelected", positionDirectorSelected)
            callbackContenido.launch(intent)
        }
        registerForContextMenu(listView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_pelicula, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        positionPeliculaSelected = info.position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuEditarPelicula -> {
                val intent = Intent(this, FormPelicula::class.java)
                intent.putExtra("positionPeliculaSelected", positionPeliculaSelected)
                intent.putExtra("positionDirectorSelected", positionDirectorSelected)
                callbackContenido.launch(intent)
                true
            }

            R.id.menuEliminarPelicula -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("¿Desea eliminar esta película?")
                builder.setPositiveButton("Si") { dialog, which ->
                    peliculaDAO.eliminarPelicula(
                        directorDAO.obtenerDirectores()[positionDirectorSelected].peliculas[positionPeliculaSelected].id
                    )
                    adapter.notifyDataSetChanged()
                    Toast.makeText(
                        this,
                        "Película eliminada",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                builder.setNegativeButton("No") { dialog, which ->
                    Toast.makeText(this, "Operación cancelada", Toast.LENGTH_SHORT).show()
                }
                builder.create().show()
                true
            }

            else -> super.onContextItemSelected(item)
        }
    }
}